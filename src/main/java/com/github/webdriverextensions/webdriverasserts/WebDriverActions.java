package com.github.webdriverextensions.webdriverasserts;

import com.github.webdriverextensions.webdriverasserts.internal.BotUtils;
import com.github.webdriverextensions.webdriverasserts.internal.NumberUtils;
import com.google.common.base.Predicate;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.github.webdriverextensions.webdriverasserts.internal.BotUtils.asNanos;
import static com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote;
import static org.apache.commons.lang3.math.NumberUtils.createDouble;

public class WebDriverActions {

    private static InheritableThreadLocal<WebDriver> threadLocalDriver = new InheritableThreadLocal<>();

    public static void removeDriver() {
        threadLocalDriver.remove();
    }

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    /* Driver */
    private static WebDriver driver() {
        if (threadLocalDriver.get() == null) {
            throw new WebDriverException("Driver in WebDriverActions is not set. Please set the driver with WebDriverActions.setDriver(...) method before using the WebDriverActions asserts. Note that the driver will be thread safe since ThreadLocal is used so don't worry about thread safety.");
        }
        return threadLocalDriver.get();
    }



    /* Click */
    public static void click(WebElement webElement) {
        webElement.click();
    }



    /* Double Click */
    public static void doubleClick(WebElement webElement) {
	Actions action = new Actions(driver());
	action.doubleClick(webElement).perform();
    }



    /* Type */
    public static void type(String text, WebElement webElement) {
        if (text == null) {
            return;
        }
        webElement.sendKeys(text);
    }

    public static void type(double number, WebElement webElement) {
        type(NumberUtils.toString(number), webElement);
    }



    /* Clear */
    public static void clear(WebElement webElement) {
        webElement.clear();
    }

    public static void clearAndType(String text, WebElement webElement) {
        clear(webElement);
        type(text, webElement);
    }

    public static void clearAndType(double number, WebElement webElement) {
        clear(webElement);
        type(number, webElement);
    }



    /* Press Keys */
    public static void pressEnter(WebElement webElement) {
        pressKeys(webElement, Keys.ENTER);
    }

    public static void pressKeys(WebElement webElement, CharSequence... keys) {
        webElement.sendKeys(keys);
    }



    /* Select/Deselect */
    public static void select(WebElement webElement) {
        if (isDeselected(webElement)) {
            webElement.click();
        }
    }

    public static void deselect(WebElement webElement) {
        if (isSelected(webElement)) {
            webElement.click();
        }
    }

    public static void selectOption(String text, WebElement webElement) {
        new Select(webElement).selectByVisibleText(text);
    }

    public static void deselectOption(String text, WebElement webElement) {
        new Select(webElement).deselectByVisibleText(text);
    }

    public static void selectAllOptions(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            select(webElement);
        }
    }

    public static void deselectAllOptions(WebElement webElement) {
        new Select(webElement).deselectAll();
    }

    public static void selectOptionWithValue(String value, WebElement webElement) {
        new Select(webElement).selectByValue(value);
    }

    public static void deselectOptionWithValue(String value, WebElement webElement) {
        new Select(webElement).deselectByValue(value);
    }

    public static void selectOptionWithIndex(int index, WebElement webElement) {
        new Select(webElement).selectByIndex(index);
    }

    public static void deselectOptionWithIndex(int index, WebElement webElement) {
        new Select(webElement).selectByIndex(index);
    }



    /* Check/Uncheck */
    public static void check(WebElement webElement) {
        if (isUnchecked(webElement)) {
            click(webElement);
        }
    }

    public static void uncheck(WebElement webElement) {
        if (isChecked(webElement)) {
            click(webElement);
        }
    }



    /* Navigation */
    public static void navigateBack() {
        driver().navigate().back();
    }
    public static void navigateForward() {
        driver().navigate().forward();
    }
    public static void navigateRefresh() {
        driver().navigate().refresh();
    }



    /* Wait For */
    public static void waitFor(double seconds) {
        long nanos = (long) (seconds * 1000000000);
        if (seconds > 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(nanos);
            } catch (InterruptedException ex) {
                // Swallow exception
                ex.printStackTrace();
            }
        }
    }

    public static void waitFor(double time, TimeUnit unit) {
        if (time <= 0) {
            return;
        }
        try {
            TimeUnit.NANOSECONDS.sleep(asNanos(time, unit));
        } catch (InterruptedException ex) {
            // Swallow exception
            ex.printStackTrace();
        }
    }

    public static void waitForElementToDisplay(WebElement webElement) {
        waitForElementToDisplay(webElement, 30);
    }

    public static void waitForElementToDisplay(WebElement webElement, long secondsToWait) {
        WebDriverWait wait = new WebDriverWait(driver(), secondsToWait);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementToDisplay(WebElement webElement, double timeToWait, TimeUnit unit) {
        WebDriverWait wait = new WebDriverWait(driver(), asNanos(timeToWait, unit));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementToDisplay(WebElement webElement, long secondsToWait, long sleepInMillis) {
        WebDriverWait wait = new WebDriverWait(driver(), secondsToWait, sleepInMillis);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementToDisplay(WebElement webElement, double timeToWait, TimeUnit unit, long sleepInMillis) {
        WebDriverWait wait = new WebDriverWait(driver(), asNanos(timeToWait, unit), sleepInMillis);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementsToDisplay(List<WebElement> webElements) {
        waitForElementsToDisplay((List<WebElement>) webElements, 30);
    }

    public static void waitForElementsToDisplay(List<WebElement> webElements, long secondsToWait) {
        WebDriverWait wait = new WebDriverWait(driver(), secondsToWait);
        wait.until(ExpectedConditions.visibilityOfAllElements((List<WebElement>) webElements));
    }

    public static void waitForElementsToDisplay(List<WebElement> webElements, long secondsToWait, long sleepInMillis) {
        WebDriverWait wait = new WebDriverWait(driver(), secondsToWait, sleepInMillis);
        wait.until(ExpectedConditions.visibilityOfAllElements((List<WebElement>) webElements));
    }



    /* Wait Until */
    public static void waitUntil(Predicate<WebDriver> perdicate) {
	waitUntil(perdicate, 30);
    }

    public static void waitUntil(Predicate<WebDriver> perdicate, long secondsToWait) {
	new WebDriverWait(driver(), secondsToWait).until(perdicate);
    }



    /* Scrolling */
    public static Object scrollTo(WebElement webElement) {
        return executeJavascript("arguments[0].scrollIntoView(true);", webElement);
    }



    /* Execute Javascript */
    public static Object executeJavascript(String script, Object... arguments) {
        return ((JavascriptExecutor) driver()).executeScript(script, arguments);
    }

    public static Object executeJavascriptAsynchronously(String script, Object... arguments) {
        return ((JavascriptExecutor) driver()).executeAsyncScript(script, arguments);
    }



    /* Browser */
    public static String browser() {
        return ((HasCapabilities) driver()).getCapabilities().getBrowserName();
    }

    public static boolean browserIs(String browserName) {
        return StringUtils.equalsIgnoreCase(browser(), browserName);
    }

    public static boolean browserIsNot(String browserName) {
        return !browserIs(browserName);
    }

    public static boolean browserIsAndroid() {
        return browserIs(BrowserType.ANDROID);
    }

    public static boolean browserIsNotAndroid() {
        return !browserIsAndroid();
    }

    public static boolean browserIsChrome() {
        return browserIs(BrowserType.CHROME) || browserIs(BrowserType.GOOGLECHROME);
    }

    public static boolean browserIsNotChrome() {
        return !browserIsChrome();
    }

    public static boolean browserIsEdge() {
        return browserIs(BrowserType.EDGE);
    }

    public static boolean browserIsNotEdge() {
        return !browserIsEdge();
    }

    public static boolean browserIsFirefox() {
        return browserIs(BrowserType.FIREFOX);
    }

    public static boolean browserIsNotFirefox() {
        return !browserIsFirefox();
    }

    public static boolean browserIsHtmlUnit() {
        return browserIs(BrowserType.HTMLUNIT);
    }

    public static boolean browserIsNotHtmlUnit() {
        return !browserIsHtmlUnit();
    }

    public static boolean browserIsIPad() {
        return browserIs(BrowserType.IPAD);
    }

    public static boolean browserIsNotIPad() {
        return !browserIsIPad();
    }

    public static boolean browserIsIPhone() {
        return browserIs(BrowserType.IPHONE);
    }

    public static boolean browserIsNotIPhone() {
        return !browserIsIPhone();
    }

    public static boolean browserIsInternetExplorer() {
        return browserIs(BrowserType.IE) || browserIs(BrowserType.IEXPLORE);
    }

    public static boolean browserIsNotInternetExplorer() {
        return !browserIsInternetExplorer();
    }

    public static boolean browserIsOpera() {
        return browserIs(BrowserType.OPERA);
    }

    public static boolean browserIsNotOpera() {
        return !browserIsOpera();
    }

    public static boolean browserIsPhantomJS() {
        return browserIs(BrowserType.PHANTOMJS);
    }

    public static boolean browserIsNotPhantomJS() {
        return !browserIsPhantomJS();
    }

    public static boolean browserIsSafari() {
        return browserIs(BrowserType.SAFARI);
    }

    public static boolean browserIsNotSafari() {
        return !browserIsSafari();
    }



    /* Version */
    public static String version() {
        return ((HasCapabilities) driver()).getCapabilities().getVersion();
    }

    public static boolean versionIs(String version) {
        return StringUtils.equalsIgnoreCase(version(), version);
    }

    public static boolean versionIsNot(String version) {
        return !versionIs(version);
    }



    /* Platform */
    public static Platform platform() {
        return ((HasCapabilities) driver()).getCapabilities().getPlatform();
    }

    public static boolean platformIs(Platform platform) {
        return platform().is(platform);
    }

    public static boolean platformIsNot(Platform platform) {
        return !platformIs(platform);
    }

    public static boolean platformIsAndroid() {
        return platformIs(Platform.ANDROID);
    }

    public static boolean platformIsNotAndroid() {
        return !platformIsAndroid();
    }

    public static boolean platformIsLinux() {
        return platformIs(Platform.LINUX);
    }

    public static boolean platformIsNotLinux() {
        return !platformIsLinux();
    }

    public static boolean platformIsMac() {
        return platformIs(Platform.MAC);
    }

    public static boolean platformIsNotMac() {
        return !platformIsMac();
    }

    public static boolean platformIsUnix() {
        return platformIs(Platform.UNIX);
    }

    public static boolean platformIsNotUnix() {
        return !platformIsUnix();
    }

    public static boolean platformIsWindows() {
        return platformIs(Platform.WINDOWS);
    }

    public static boolean platformIsNotWindows() {
        return !platformIsWindows();
    }

    public static boolean platformIsWin8() {
        return platformIs(Platform.WIN8);
    }

    public static boolean platformIsNotWin8() {
        return !platformIsWin8();
    }

    public static boolean platformIsWin8_1() {
        return platformIs(Platform.WIN8_1);
    }

    public static boolean platformIsNotWin8_1() {
        return !platformIsWin8_1();
    }

    public static boolean platformIsWin10() {
        return platformIs(Platform.WIN10);
    }

    public static boolean platformIsNotWin10() {
        return !platformIsWin10();
    }

    public static boolean platformIsVista() {
        return platformIs(Platform.VISTA);
    }

    public static boolean platformIsNotVista() {
        return !platformIsVista();
    }

    public static boolean platformIsXP() {
        return platformIs(Platform.XP);
    }

    public static boolean platformIsNotXP() {
        return !platformIsXP();
    }



    /* Take Screenshot */
    /**
     * Takes a screenshot and saves it as a file in a directory called {@code screenshots}.
     * A custom path to the directory where the screenshots are saved can be set by setting
     * the system property {@code webdriverextensions.screenshotspath}.
     *
     * @param fileName the filename of the screenshot file without the file extension
     */
    public static void takeScreenshot(String fileName) {
        File screenshotFile = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.FILE);
        String filePath = getScreenshotFilePath(fileName);
        try {
            FileUtils.copyFile(screenshotFile, new File(filePath));
        } catch (IOException ex) {
            throw new RuntimeException("Failed to save screenshot to " + quote(filePath), ex);
        }
    }

    private static String getScreenshotFilePath(String fileName) {
        return null;
    }


    /* To String */
    public static String htmlOf(WebElement webElement) {
        return BotUtils.htmlOf(webElement);
    }

    public static String htmlOf(List<WebElement> webElements) {
        StringBuilder builder = new StringBuilder();
        for (WebElement webElement : webElements) {
            builder.append(htmlOf(webElement));
        }
        return builder.toString();
    }



    /* Is Displayed */
    public static boolean isDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isNotDisplayed(WebElement webElement) {
        return !isDisplayed(webElement);
    }

    public static boolean isDisplayed(WebElement webElement, long secondsToWait) {
        try {
            WebElement foundWebElement = new WebDriverWait(driver(), secondsToWait).until(ExpectedConditions.visibilityOf(webElement));

            return foundWebElement != null;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static boolean isNotDisplayed(WebElement webElement, long secondsToWait) {
        return !isDisplayed(webElement, secondsToWait);
    }



    /* Size */
    public static int sizeOf(Collection collection) {
        return collection.size();
    }

    public static boolean sizeEquals(int number, Collection collection) {
        return BotUtils.isEqual((double) number, (double) collection.size());
    }

    public static boolean sizeNotEquals(int number, Collection collection) {
        return BotUtils.notEquals((double) number, (double) collection.size());
    }

    public static boolean sizeLessThan(int number, Collection collection) {
        return BotUtils.lessThan((double) number, (double) collection.size());
    }

    public static boolean sizeLessThanOrEquals(int number, Collection collection) {
        return BotUtils.lessThanOrEquals((double) number, (double) collection.size());
    }

    public static boolean sizeGreaterThan(int number, Collection collection) {
        return BotUtils.greaterThan((double) number, (double) collection.size());
    }

    public static boolean sizeGreaterThanOrEquals(int number, Collection collection) {
        return BotUtils.greaterThanOrEquals((double) number, (double) collection.size());
    }


    /* Current Url */
    public static String currentUrl() {
        return driver().getCurrentUrl();
    }

    public static boolean currentUrlEquals(String url) {
        return BotUtils.isEqual(url, currentUrl());
    }

    public static boolean currentUrlNotEquals(String url) {
        return BotUtils.notEquals(url, currentUrl());
    }

    public static boolean currentUrlContains(String searchText) {
        return BotUtils.contains(searchText, currentUrl());
    }

    public static boolean currentUrlNotContains(String searchText) {
        return BotUtils.notContains(searchText, currentUrl());
    }

    public static boolean currentUrlStartsWith(String prefix) {
        return BotUtils.startsWith(prefix, currentUrl());
    }

    public static boolean currentUrlNotStartsWith(String prefix) {
        return BotUtils.notStartsWith(prefix, currentUrl());
    }

    public static boolean currentUrlEndsWith(String suffix) {
        return BotUtils.endsWith(suffix, currentUrl());
    }

    public static boolean currentUrlNotEndsWith(String suffix) {
        return BotUtils.notEndsWith(suffix, currentUrl());
    }

    public static boolean currentUrlMatches(String regExp) {
        return BotUtils.matches(regExp, currentUrl());
    }

    public static boolean currentUrlNotMatches(String regExp) {
        return BotUtils.notMatches(regExp, currentUrl());
    }



    /* Title */
    public static String title() {
        return driver().getTitle();
    }

    public static boolean titleEquals(String title) {
        return BotUtils.isEqual(title, title());
    }

    public static boolean titleNotEquals(String title) {
        return BotUtils.notEquals(title, title());
    }

    public static boolean titleContains(String searchText) {
        return BotUtils.contains(searchText, title());
    }

    public static boolean titleNotContains(String searchText) {
        return BotUtils.notContains(searchText, title());
    }

    public static boolean titleStartsWith(String prefix) {
        return BotUtils.startsWith(prefix, title());
    }

    public static boolean titleNotStartsWith(String prefix) {
        return BotUtils.notStartsWith(prefix, title());
    }

    public static boolean titleEndsWith(String suffix) {
        return BotUtils.endsWith(suffix, title());
    }

    public static boolean titleNotEndsWith(String suffix) {
        return BotUtils.notEndsWith(suffix, title());
    }

    public static boolean titleMatches(String regExp) {
        return BotUtils.matches(regExp, title());
    }

    public static boolean titleNotMatches(String regExp) {
        return BotUtils.notMatches(regExp, title());
    }



    /* Tag Name */
    public static String tagNameOf(WebElement webElement) {
        return webElement.getTagName();
    }

    public static boolean tagNameEquals(String value, WebElement webElement) {
        return BotUtils.isEqual(value, tagNameOf(webElement));
    }

    public static boolean tagNameNotEquals(String value, WebElement webElement) {
        return BotUtils.notEquals(value, tagNameOf(webElement));
    }



    /* Attribute */
    /**
     * Returns a {@link WebElement} attribute value.
     *
     * <p>If the {@link WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input title="Some title"/>
     * attributeIn("title", input) = "Some title"
     *
     * no input in html
     * attributeIn("title", "input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link WebElement} containing
     * an attribute to return
     * @return the id attribute
     */
    public static String attributeIn(String name, WebElement webElement) {
        return webElement.getAttribute(name);
    }

    public static boolean hasAttribute(String name, WebElement webElement) {
        return webElement.getAttribute(name) != null;
    }

    public static boolean hasNotAttribute(String name, WebElement webElement) {
        return !hasAttribute(name, webElement);
    }

    public static boolean attributeEquals(String name, String value, WebElement webElement) {
        return BotUtils.isEqual(value, attributeIn(name, webElement));
    }

    public static boolean attributeNotEquals(String name, String value, WebElement webElement) {
        return BotUtils.notEquals(value, attributeIn(name, webElement));
    }

    public static boolean attributeContains(String name, String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, attributeIn(name, webElement));
    }

    public static boolean attributeNotContains(String name, String searchText, WebElement webElement) {
        return BotUtils.notContains(searchText, attributeIn(name, webElement));
    }

    public static boolean attributeStartsWith(String name, String prefix, WebElement webElement) {
        return BotUtils.startsWith(prefix, attributeIn(name, webElement));
    }

    public static boolean attributeNotStartsWith(String name, String prefix, WebElement webElement) {
        return BotUtils.notStartsWith(prefix, attributeIn(name, webElement));
    }

    public static boolean attributeEndsWith(String name, String suffix, WebElement webElement) {
        return BotUtils.endsWith(suffix, attributeIn(name, webElement));
    }

    public static boolean attributeNotEndsWith(String name, String suffix, WebElement webElement) {
        return BotUtils.notEndsWith(suffix, attributeIn(name, webElement));
    }

    public static boolean attributeMatches(String name, String regExp, WebElement webElement) {
        return BotUtils.matches(regExp, attributeIn(name, webElement));
    }

    public static boolean attributeNotMatches(String name, String regExp, WebElement webElement) {
        return BotUtils.notMatches(regExp, attributeIn(name, webElement));
    }



    /* Attribute as Number */
    public static double attributeInAsNumber(String name, WebElement webElement) {
        return createDouble(attributeIn(name, webElement));
    }

    public static boolean attributeIsNumber(String name, WebElement webElement) {
        try {
            attributeInAsNumber(name, webElement);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean attributeIsNotNumber(String name, WebElement webElement) {
        return !attributeIsNumber(name, webElement);
    }

    public static boolean attributeEquals(String name, double number, WebElement webElement) {
        return BotUtils.isEqual(number, attributeInAsNumber(name, webElement));
    }

    public static boolean attributeNotEquals(String name, double number, WebElement webElement) {
        return BotUtils.notEquals(number, attributeInAsNumber(name, webElement));
    }

    public static boolean attributeLessThan(String name, double number, WebElement webElement) {
        return BotUtils.lessThan(number, attributeInAsNumber(name, webElement));
    }

    public static boolean attributeLessThanOrEquals(String name, double number, WebElement webElement) {
        return BotUtils.lessThanOrEquals(number, attributeInAsNumber(name, webElement));
    }

    public static boolean attributeGreaterThan(String name, double number, WebElement webElement) {
        return BotUtils.greaterThan(number, attributeInAsNumber(name, webElement));
    }

    public static boolean attributeGreaterThanOrEquals(String name, double number, WebElement webElement) {
        return BotUtils.greaterThanOrEquals(number, attributeInAsNumber(name, webElement));
    }



    /* Id */
    /**
     * Returns the {@link WebElement} id attribute.
     *
     * <p>If the {@link WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input id="some-id"/>
     * idIn(input) = "some-id"
     *
     * no input in html
     * idIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link WebElement} containing
     * an id attribute
     * @return the id attribute
     */
    public static String idIn(WebElement webElement) {
        return attributeIn("id", webElement);
    }

    public static boolean hasId(WebElement webElement) {
        return hasAttribute("id", webElement);
    }

    public static boolean hasNotId(WebElement webElement) {
        return hasNotAttribute("id", webElement);
    }

    public static boolean idEquals(String value, WebElement webElement) {
        return attributeEquals("id", value, webElement);
    }

    public static boolean idNotEquals(String value, WebElement webElement) {
        return attributeNotEquals("id", value, webElement);
    }

    public static boolean idContains(String searchText, WebElement webElement) {
        return attributeContains("id", searchText, webElement);
    }

    public static boolean idNotContains(String searchText, WebElement webElement) {
        return attributeNotContains("id", searchText, webElement);
    }

    public static boolean idStartsWith(String prefix, WebElement webElement) {
        return attributeStartsWith("id", prefix, webElement);
    }

    public static boolean idNotStartsWith(String prefix, WebElement webElement) {
        return attributeNotStartsWith("id", prefix, webElement);
    }

    public static boolean idEndsWith(String suffix, WebElement webElement) {
        return attributeEndsWith("id", suffix, webElement);
    }

    public static boolean idNotEndsWith(String suffix, WebElement webElement) {
        return attributeNotEndsWith("id", suffix, webElement);
    }

    public static boolean idMatches(String regExp, WebElement webElement) {
        return attributeMatches("id", regExp, webElement);
    }

    public static boolean idNotMatches(String regExp, WebElement webElement) {
        return attributeNotMatches("id", regExp, webElement);
    }



    /* Name */
    /**
     * Returns the {@link WebElement} name attribute.
     *
     * <p>If the {@link WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input name="some-name"/>
     * nameIn(input) = "some-name"
     *
     * no input in html
     * nameIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link WebElement} containing
     * a name attribute
     * @return the name attribute
     */
    public static String nameIn(WebElement webElement) {
        return attributeIn("name", webElement);
    }

    public static boolean hasName(WebElement webElement) {
        return hasAttribute("name", webElement);
    }

    public static boolean hasNotName(WebElement webElement) {
        return hasNotAttribute("name", webElement);
    }

    public static boolean nameEquals(String value, WebElement webElement) {
        return attributeEquals("name", value, webElement);
    }

    public static boolean nameNotEquals(String value, WebElement webElement) {
        return attributeNotEquals("name", value, webElement);
    }

    public static boolean nameContains(String searchText, WebElement webElement) {
        return attributeContains("name", searchText, webElement);
    }

    public static boolean nameNotContains(String searchText, WebElement webElement) {
        return attributeNotContains("name", searchText, webElement);
    }

    public static boolean nameStartsWith(String prefix, WebElement webElement) {
        return attributeStartsWith("name", prefix, webElement);
    }

    public static boolean nameNotStartsWith(String prefix, WebElement webElement) {
        return attributeNotStartsWith("name", prefix, webElement);
    }

    public static boolean nameEndsWith(String suffix, WebElement webElement) {
        return attributeEndsWith("name", suffix, webElement);
    }

    public static boolean nameNotEndsWith(String suffix, WebElement webElement) {
        return attributeNotEndsWith("name", suffix, webElement);
    }

    public static boolean nameMatches(String regExp, WebElement webElement) {
        return attributeMatches("name", regExp, webElement);
    }

    public static boolean nameNotMatches(String regExp, WebElement webElement) {
        return attributeNotMatches("name", regExp, webElement);
    }



    /* Class */
    /**
     * Returns the {@link WebElement} class attribute.
     *
     * <p>If the {@link WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input class="a-class another-class"/>
     * classIn(input) = "a-class another-class"
     *
     * no input in html
     * classIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link WebElement} containing
     * a class attribute
     * @return the class attribute
     */
    public static String classIn(WebElement webElement) {
        return attributeIn("class", webElement);
    }

    /**
     * Returns the classes in the {@link WebElement} class
     * attribute.
     *
     * <p>If the {@link WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input class=" a-class   another-class "/>
     * classesIn(input) = "a-class", "another-class"
     *
     * no input in html
     * classIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link WebElement} containing
     * a class attribute
     * @return the classes in the class attribute
     */
    public static List<String> classesIn(WebElement webElement) {
        return Arrays.asList(StringUtils.split(classIn(webElement)));
    }

    public static boolean hasClass(WebElement webElement) {
        return hasAttribute("class", webElement);
    }

    public static boolean hasNotClass(WebElement webElement) {
        return hasNotAttribute("class", webElement);
    }

    public static boolean hasClass(String className, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.isEqual(className, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClass(String className, WebElement webElement) {
        return !hasClass(className, webElement);
    }

    public static boolean hasClassContaining(String searchText, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.contains(searchText, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClassContaining(String searchText, WebElement webElement) {
        return !hasClassContaining(searchText, webElement);
    }

    public static boolean hasClassStartingWith(String prefix, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.startsWith(prefix, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClassStartingWith(String prefix, WebElement webElement) {
        return !hasClassStartingWith(prefix, webElement);
    }

    public static boolean hasClassEndingWith(String suffix, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.endsWith(suffix, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClassEndingWith(String suffix, WebElement webElement) {
        return !hasClassEndingWith(suffix, webElement);
    }

    public static boolean hasClassMatching(String regExp, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.matches(regExp, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClassMatching(String regExp, WebElement webElement) {
        return !hasClassMatching(regExp, webElement);
    }



    /* Value */
    /**
     * Returns the {@link WebElement} value attribute.
     *
     * <p>If the {@link WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input value="Some value"/>
     * valueIn(input) = "Some value"
     *
     * no input in html
     * valueIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link WebElement} containing
     * a value attribute
     * @return the value attribute
     */
    public static String valueIn(WebElement webElement) {
        return attributeIn("value", webElement);
    }

    public static boolean hasValue(WebElement webElement) {
        return hasAttribute("value", webElement);
    }

    public static boolean hasNotValue(WebElement webElement) {
        return hasNotAttribute("value", webElement);
    }

    public static boolean valueEquals(String value, WebElement webElement) {
        return attributeEquals("value", value, webElement);
    }

    public static boolean valueNotEquals(String value, WebElement webElement) {
        return attributeNotEquals("value", value, webElement);
    }

    public static boolean valueContains(String searchText, WebElement webElement) {
        return attributeContains("value", searchText, webElement);
    }

    public static boolean valueNotContains(String searchText, WebElement webElement) {
        return attributeNotContains("value", searchText, webElement);
    }

    public static boolean valueStartsWith(String prefix, WebElement webElement) {
        return attributeStartsWith("value", prefix, webElement);
    }

    public static boolean valueNotStartsWith(String prefix, WebElement webElement) {
        return attributeNotStartsWith("value", prefix, webElement);
    }

    public static boolean valueEndsWith(String suffix, WebElement webElement) {
        return attributeEndsWith("value", suffix, webElement);
    }

    public static boolean valueNotEndsWith(String suffix, WebElement webElement) {
        return attributeNotEndsWith("value", suffix, webElement);
    }

    public static boolean valueMatches(String regExp, WebElement webElement) {
        return attributeMatches("value", regExp, webElement);
    }

    public static boolean valueNotMatches(String regExp, WebElement webElement) {
        return attributeNotMatches("value", regExp, webElement);
    }



    /* Value as Number */
    /**
     * Returns the {@link WebElement} value attribute as a
     * number.
     *
     * <p>If the value attribute in the {@link WebElement}
     * does not contain a valid number a {@code java.util.NumberFormatException}
     * will be thrown. If the {@link WebElement} does not
     * exist in the html a {@code org.openqa.selenium.NoSuchElementException}
     * will be thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input value="42"/>
     * valueInAsNumber(input) = 42.0
     *
     * <input value="Some value"/>
     * valueInAsNumber(input) throws java.util.NumberFormatException
     *
     * <input value=""/>
     * valueInAsNumber(input) throws java.util.NumberFormatException
     *
     * no input in html
     * valueInAsNumber(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link WebElement} containing
     * a value attribute with a number
     * @return the value attribute as a number
     */
    public static double valueInAsNumber(WebElement webElement) {
        return attributeInAsNumber("value", webElement);
    }

    public static boolean valueIsNumber(WebElement webElement) {
        return attributeIsNumber("value", webElement);
    }

    public static boolean valueIsNotNumber(WebElement webElement) {
        return attributeIsNotNumber("value", webElement);
    }

    public static boolean valueEquals(double number, WebElement webElement) {
        return attributeEquals("value", number, webElement);
    }

    public static boolean valueNotEquals(double number, WebElement webElement) {
        return attributeNotEquals("value", number, webElement);
    }

    public static boolean valueLessThan(double number, WebElement webElement) {
        return attributeLessThan("value", number, webElement);
    }

    public static boolean valueLessThanOrEquals(double number, WebElement webElement) {
        return attributeLessThanOrEquals("value", number, webElement);
    }

    public static boolean valueGreaterThan(double number, WebElement webElement) {
        return attributeGreaterThan("value", number, webElement);
    }

    public static boolean valueGreaterThanOrEquals(double number, WebElement webElement) {
        return attributeGreaterThanOrEquals("value", number, webElement);
    }



    /* Href */
    /**
     * Returns the {@link WebElement} href attribute.
     *
     * <p>If the {@link WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input href="www.href.com"/>
     * hrefIn(input) = "www.href.com"
     *
     * no input in html
     * hrefIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link WebElement} containing
     * a href attribute
     * @return the href attribute
     */
    public static String hrefIn(WebElement webElement) {
        return attributeIn("href", webElement);
    }

    public static boolean hasHref(WebElement webElement) {
        return hasAttribute("href", webElement);
    }

    public static boolean hasNotHref(WebElement webElement) {
        return hasNotAttribute("href", webElement);
    }

    public static boolean hrefEquals(String value, WebElement webElement) {
        return attributeEquals("href", value, webElement);
    }

    public static boolean hrefNotEquals(String value, WebElement webElement) {
        return attributeNotEquals("href", value, webElement);
    }

    public static boolean hrefContains(String searchText, WebElement webElement) {
        return attributeContains("href", searchText, webElement);
    }

    public static boolean hrefNotContains(String searchText, WebElement webElement) {
        return attributeNotContains("href", searchText, webElement);
    }

    public static boolean hrefStartsWith(String prefix, WebElement webElement) {
        return attributeStartsWith("href", prefix, webElement);
    }

    public static boolean hrefNotStartsWith(String prefix, WebElement webElement) {
        return attributeNotStartsWith("href", prefix, webElement);
    }

    public static boolean hrefEndsWith(String suffix, WebElement webElement) {
        return attributeEndsWith("href", suffix, webElement);
    }

    public static boolean hrefNotEndsWith(String suffix, WebElement webElement) {
        return attributeNotEndsWith("href", suffix, webElement);
    }

    public static boolean hrefMatches(String regExp, WebElement webElement) {
        return attributeMatches("href", regExp, webElement);
    }

    public static boolean hrefNotMatches(String regExp, WebElement webElement) {
        return attributeNotMatches("href", regExp, webElement);
    }



    /* Text */
    /**
     * Returns the visible text in a {@link WebElement}.
     *
     * <p>If the {@link WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <span>Some text</span>
     * textIn(span) = "Some text"
     *
     * <span>
     *     Some text containing <b>html</b>
     * </span>
     * textIn(span) = "Some text containing html"
     *
     * <span style="display: none">Some invisible text</span>
     * textIn(span) = ""
     *
     * no span in html
     * textIn(span) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link WebElement} containing
     * the text
     * @return the visible text
     */
    public static String textIn(WebElement webElement) {
        // Text is trimmed to normalize behavior since Chrome and PhantomJS driver incorrectly returns spaces around the text (Not according the the WebElement tetText docs), see bug report https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/7473 remove this when bug is solved!
        return StringUtils.trim(webElement.getText());
    }

    public static boolean hasText(WebElement webElement) {
        return BotUtils.notEquals("", textIn(webElement));
    }

    public static boolean hasNotText(WebElement webElement) {
        return BotUtils.isEqual("", textIn(webElement));
    }

    public static boolean textEquals(String text, WebElement webElement) {
        return BotUtils.isEqual(text, textIn(webElement));
    }

    public static boolean textNotEquals(String text, WebElement webElement) {
        return BotUtils.notEquals(text, textIn(webElement));
    }

    public static boolean textEqualsIgnoreCase(String text, WebElement webElement) {
        return BotUtils.equalsIgnoreCase(text, textIn(webElement));
    }

    public static boolean textNotEqualsIgnoreCase(String text, WebElement webElement) {
        return BotUtils.notEqualsIgnoreCase(text, textIn(webElement));
    }

    public static boolean textContains(String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, textIn(webElement));
    }

    public static boolean textNotContains(String searchText, WebElement webElement) {
        return BotUtils.notContains(searchText, textIn(webElement));
    }

    public static boolean textContainsIgnoreCase(String searchText, WebElement webElement) {
        return BotUtils.containsIgnoreCase(searchText, textIn(webElement));
    }

    public static boolean textNotContainsIgnoreCase(String searchText, WebElement webElement) {
        return BotUtils.notContainsIgnoreCase(searchText, textIn(webElement));
    }

    public static boolean textStartsWith(String prefix, WebElement webElement) {
        return BotUtils.startsWith(prefix, textIn(webElement));
    }

    public static boolean textNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.notStartsWith(prefix, textIn(webElement));
    }

    public static boolean textStartsWithIgnoreCase(String prefix, WebElement webElement) {
        return BotUtils.startsWithIgnoreCase(prefix, textIn(webElement));
    }

    public static boolean textNotStartsWithIgnoreCase(String prefix, WebElement webElement) {
        return BotUtils.notStartsWithIgnoreCase(prefix, textIn(webElement));
    }

    public static boolean textEndsWith(String suffix, WebElement webElement) {
        return BotUtils.endsWith(suffix, textIn(webElement));
    }

    public static boolean textNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.notEndsWith(suffix, textIn(webElement));
    }

    public static boolean textEndsWithIgnoreCase(String suffix, WebElement webElement) {
        return BotUtils.endsWithIgnoreCase(suffix, textIn(webElement));
    }

    public static boolean textNotEndsWithIgnoreCase(String suffix, WebElement webElement) {
        return BotUtils.notEndsWithIgnoreCase(suffix, textIn(webElement));
    }

    public static boolean textMatches(String regExp, WebElement webElement) {
        return BotUtils.matches(regExp, textIn(webElement));
    }

    public static boolean textNotMatches(String regExp, WebElement webElement) {
        return BotUtils.notMatches(regExp, textIn(webElement));
    }



    /* Text as Number */
    /**
     * Returns the visible text in a {@link WebElement} as a
     * number.
     *
     * <p>If the text in the {@link WebElement} does not
     * contain a valid number a {@code java.util.NumberFormatException} will be
     * thrown. If the {@link WebElement} does not exist in
     * the html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <span>42</span>
     * textInAsNumber(span) = 42.0
     *
     * <span>
     *     42
     * </span>
     * textInAsNumber(span) = 42.0
     *
     * <span>Some text</span>
     * textInAsNumber(span) throws java.util.NumberFormatException
     *
     * <span style="display: none">42</span>
     * textInAsNumber(span) throws java.util.NumberFormatException
     *
     * no span in html
     * textInAsNumber(span) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link WebElement} containing
     * a text with a number
     * @return the visible text as a number
     */
     public static double textInAsNumber(WebElement webElement) {
        return createDouble(textIn(webElement));
    }

    public static boolean textIsNumber(WebElement webElement) {
        try {
            textInAsNumber(webElement);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean textIsNotNumber(WebElement webElement) {
        return !textIsNumber(webElement);
    }

    public static boolean textEquals(double number, WebElement webElement) {
        return BotUtils.isEqual(number, textInAsNumber(webElement));
    }

    public static boolean textNotEquals(double number, WebElement webElement) {
        return BotUtils.notEquals(number, textInAsNumber(webElement));
    }

    public static boolean textLessThan(double number, WebElement webElement) {
        return BotUtils.lessThan(number, textInAsNumber(webElement));
    }

    public static boolean textLessThanOrEquals(double number, WebElement webElement) {
        return BotUtils.lessThanOrEquals(number, textInAsNumber(webElement));
    }

    public static boolean textGreaterThan(double number, WebElement webElement) {
        return BotUtils.greaterThan(number, textInAsNumber(webElement));
    }

    public static boolean textGreaterThanOrEquals(double number, WebElement webElement) {
        return BotUtils.greaterThanOrEquals(number, textInAsNumber(webElement));
    }



    /* Selected/Deselected */
    public static boolean isSelected(WebElement webElement) {
        return webElement.isSelected();
    }

    public static boolean isDeselected(WebElement webElement) {
        return !isSelected(webElement);
    }




    /* Checked/Unchecked */
    public static boolean isChecked(WebElement webElement) {
        return webElement.isSelected();
    }

    public static boolean isUnchecked(WebElement webElement) {
        return !isChecked(webElement);
    }



    /* Enabled/Disabled */
    public static boolean isEnabled(WebElement webElement) {
        return webElement.isEnabled();
    }

    public static boolean isDisabled(WebElement webElement) {
        return !isEnabled(webElement);
    }



    /* Option */
    public static boolean hasOption(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotOption(String text, WebElement webElement) {
        return !hasOption(text, webElement);
    }

    public static boolean optionIsEnabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionIsDisabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionIsSelected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionIsDeselected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean allOptionsAreSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isDeselected(option)) {
                return false;
            }
        }
        return true;
    }

    public static boolean noOptionIsSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isSelected(option)) {
                return false;
            }
        }
        return true;
    }



    /* Option Value */
    public static boolean hasOptionWithValue(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotOptionWithValue(String value, WebElement webElement) {
        return !hasOptionWithValue(value, webElement);
    }

    public static boolean optionWithValueIsEnabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionWithValueIsDisabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionWithValueIsSelected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionWithValueIsDeselected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }



    /* Option Index */
    public static boolean hasOptionWithIndex(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return options.get(index) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean hasNotOptionWithIndex(int index, WebElement webElement) {
        return !hasOptionWithIndex(index, webElement);
    }

    public static boolean optionWithIndexIsEnabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isEnabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean optionWithIndexIsDisabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDisabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean optionWithIndexIsSelected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isSelected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean optionWithIndexIsDeselected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDeselected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}
