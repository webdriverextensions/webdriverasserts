package com.github.webdriverextensions.webdriverasserts;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.github.webdriverextensions.webdriverasserts.internal.BotUtils;
import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.math.NumberUtils.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.github.webdriverextensions.webdriverasserts.internal.StringUtils.*;

public class WebDriverAsserts {

    private static InheritableThreadLocal<WebDriver> threadLocalDriver = new InheritableThreadLocal<>();

    public static void removeDriver() {
        threadLocalDriver.remove();
    }

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public static WebDriver getDriver() {
        if (threadLocalDriver.get() == null) {
            throw new WebDriverException("Driver in WebDriverActions is not set. Please set the driver with WebDriverActions.setDriver(...) method before using the WebDriverActions asserts. Note that the driver will be thread safe since ThreadLocal is used so don't worry about thread safety.");
        }
        return threadLocalDriver.get();
    }



    /* Is Displayed */
    private static boolean isDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private static boolean isNotDisplayed(WebElement webElement) {
        return !isDisplayed(webElement);
    }

    private static boolean isDisplayed(WebElement webElement, long secondsToWait) {
        try {
            WebElement foundWebElement = new WebDriverWait(getDriver(), secondsToWait).until(ExpectedConditions.visibilityOf(webElement));

            return foundWebElement != null;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private static boolean isNotDisplayed(WebElement webElement, long secondsToWait) {
        return !isDisplayed(webElement, secondsToWait);
    }

    public static void assertIsDisplayed(WebElement webElement) {
        if (isNotDisplayed(webElement)) {
            throw new WebDriverAssertionError("Element is not displayed", webElement);
        }
    }

    public static void assertIsNotDisplayed(WebElement webElement) {
        if (isDisplayed(webElement)) {
            throw new WebDriverAssertionError("Element is displayed when it shouldn't", webElement);
        }
    }

    public static void assertIsDisplayed(WebElement webElement, long secondsToWait) {
        if (isNotDisplayed(webElement, secondsToWait)) {
            throw new WebDriverAssertionError("Element is not displayed within " + secondsToWait + " seconds", webElement);
        }
    }

    public static void assertIsNotDisplayed(WebElement webElement, long secondsToWait) {
        if (isDisplayed(webElement, secondsToWait)) {
            throw new WebDriverAssertionError("Element is displayed within " + secondsToWait + " seconds when it shouldn't", webElement);
        }
    }



    /* Size */
    public static void assertSizeEquals(int number, Collection collection) {
        BotUtils.assertEquals("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeNotEquals(int number, Collection collection) {
        BotUtils.assertNotEquals("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeLessThan(int number, Collection collection) {
        BotUtils.assertLessThan("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeLessThanOrEquals(int number, Collection collection) {
        BotUtils.assertLessThanOrEquals("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeGreaterThan(int number, Collection collection) {
        BotUtils.assertGreaterThan("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeGreaterThanOrEquals(int number, Collection collection) {
        BotUtils.assertGreaterThanOrEquals("Size", (double) number, (double) collection.size());
    }


    /* Current Url */
    private static String currentUrl() {
        return getDriver().getCurrentUrl();
    }

    public static void assertCurrentUrlEquals(String url) {
        BotUtils.assertEquals("Current url", url, currentUrl());
    }

    public static void assertCurrentUrlNotEquals(String url) {
        BotUtils.assertNotEquals("Current url", url, currentUrl());
    }

    public static void assertCurrentUrlContains(String searchText) {
        BotUtils.assertContains("Current url", searchText, currentUrl());
    }

    public static void assertCurrentUrlNotContains(String searchText) {
        BotUtils.assertNotContains("Current url", searchText, currentUrl());
    }

    public static void assertCurrentUrlStartsWith(String prefix) {
        BotUtils.assertStartsWith("Current url", prefix, currentUrl());
    }

    public static void assertCurrentUrlNotStartsWith(String prefix) {
        BotUtils.assertNotStartsWith("Current url", prefix, currentUrl());
    }

    public static void assertCurrentUrlEndsWith(String suffix) {
        BotUtils.assertEndsWith("Current url", suffix, currentUrl());
    }

    public static void assertCurrentUrlNotEndsWith(String suffix) {
        BotUtils.assertNotEndsWith("Current url", suffix, currentUrl());
    }

    public static void assertCurrentUrlMatches(String regExp) {
        BotUtils.assertMatches("Current url", regExp, currentUrl());
    }

    public static void assertCurrentUrlNotMatches(String regExp) {
        BotUtils.assertNotMatches("Current url", regExp, currentUrl());
    }



    /* Title */
    private static String title() {
        return getDriver().getTitle();
    }

    public static void assertTitleEquals(String title) {
        BotUtils.assertEquals("Title", title, title());
    }

    public static void assertTitleNotEquals(String title) {
        BotUtils.assertNotEquals("Title", title, title());
    }

    public static void assertTitleContains(String searchText) {
        BotUtils.assertContains("Title", searchText, title());
    }

    public static void assertTitleNotContains(String searchText) {
        BotUtils.assertNotContains("Title", searchText, title());
    }

    public static void assertTitleStartsWith(String prefix) {
        BotUtils.assertStartsWith("Title", prefix, title());
    }

    public static void assertTitleNotStartsWith(String prefix) {
        BotUtils.assertNotStartsWith("Title", prefix, title());
    }

    public static void assertTitleEndsWith(String suffix) {
        BotUtils.assertEndsWith("Title", suffix, title());
    }

    public static void assertTitleNotEndsWith(String suffix) {
        BotUtils.assertNotEndsWith("Title", suffix, title());
    }

    public static void assertTitleMatches(String regExp) {
        BotUtils.assertMatches("Title", regExp, title());
    }

    public static void assertTitleNotMatches(String regExp) {
        BotUtils.assertNotMatches("Title", regExp, title());
    }



    /* Tag Name */
    private static String tagNameOf(WebElement webElement) {
        return webElement.getTagName();
    }

    public static void assertTagNameEquals(String value, WebElement webElement) {
        BotUtils.assertEquals("Tag name", value, tagNameOf(webElement), webElement);
    }

    public static void assertTagNameNotEquals(String value, WebElement webElement) {
        BotUtils.assertNotEquals("Tag name", value, tagNameOf(webElement), webElement);
    }



    /* Attribute */
    private static String attributeIn(String name, WebElement webElement) {
        return webElement.getAttribute(name);
    }

    private static boolean hasAttribute(String name, WebElement webElement) {
        return webElement.getAttribute(name) != null;
    }

    private static boolean hasNotAttribute(String name, WebElement webElement) {
        return !hasAttribute(name, webElement);
    }

    private static boolean attributeEquals(String name, String value, WebElement webElement) {
        return BotUtils.isEqual(value, attributeIn(name, webElement));
    }

    public static void assertHasAttribute(String name, WebElement webElement) {
        if (hasNotAttribute(name, webElement)) {
            throw new WebDriverAssertionError("Element does not have attribute " + quote(name), webElement);
        }
    }

    public static void assertHasNotAttribute(String name, WebElement webElement) {
        if (hasAttribute(name, webElement)) {
            throw new WebDriverAssertionError("Element has attribute " + quote(name) + " when it shouldn't", webElement);
        }
    }

    public static void assertAttributeEquals(String name, String value, WebElement webElement) {
        BotUtils.assertEquals("Element attribute " + name, value, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeNotEquals(String name, String value, WebElement webElement) {
        BotUtils.assertNotEquals("Element attribute " + name, value, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeContains(String name, String searchText, WebElement webElement) {
        BotUtils.assertContains("Element attribute " + name, searchText, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeNotContains(String name, String searchText, WebElement webElement) {
        BotUtils.assertNotContains("Element attribute " + name, searchText, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeStartsWith(String name, String prefix, WebElement webElement) {
        BotUtils.assertStartsWith("Element attribute " + name, prefix, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeNotStartsWith(String name, String prefix, WebElement webElement) {
        BotUtils.assertNotStartsWith("Element attribute " + name, prefix, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeEndsWith(String name, String suffix, WebElement webElement) {
        BotUtils.assertEndsWith("Element attribute " + name, suffix, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeNotEndsWith(String name, String suffix, WebElement webElement) {
        BotUtils.assertNotEndsWith("Element attribute " + name, suffix, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeMatches(String name, String regExp, WebElement webElement) {
        BotUtils.assertMatches("Element attribute " + name, regExp, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeNotMatches(String name, String regExp, WebElement webElement) {
        BotUtils.assertNotMatches("Element attribute " + name, regExp, attributeIn(name, webElement), webElement);
    }



    /* Attribute as Number */
    private static double attributeInAsNumber(String name, WebElement webElement) {
        return createDouble(attributeIn(name, webElement));
    }

    private static boolean attributeIsNumber(String name, WebElement webElement) {
        try {
            attributeInAsNumber(name, webElement);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean attributeIsNotNumber(String name, WebElement webElement) {
        return !attributeIsNumber(name, webElement);
    }

    public static void assertAttributeIsNumber(String name, WebElement webElement) {
        if (attributeIsNotNumber(name, webElement)) {
            throw new WebDriverAssertionError("Element attribute " + name + " is not a number", webElement);
        }
    }

    public static void assertAttributeIsNotNumber(String name, WebElement webElement) {
        if (attributeIsNumber(name, webElement)) {
            throw new WebDriverAssertionError("Element attribute " + name + " is a number when it shouldn't", webElement);
        }
    }

    public static void assertAttributeEquals(String name, double number, WebElement webElement) {
        BotUtils.assertEquals(name, number, attributeInAsNumber(name, webElement), webElement);
    }

    public static void assertAttributeNotEquals(String name, double number, WebElement webElement) {
        BotUtils.assertNotEquals(name, number, attributeInAsNumber(name, webElement), webElement);
    }

    public static void assertAttributeLessThan(String name, double number, WebElement webElement) {
        BotUtils.assertLessThan(name, number, attributeInAsNumber(name, webElement), webElement);
    }

    public static void assertAttributeLessThanOrEquals(String name, double number, WebElement webElement) {
        BotUtils.assertLessThanOrEquals(name, number, attributeInAsNumber(name, webElement), webElement);
    }

    public static void assertAttributeGreaterThan(String name, double number, WebElement webElement) {
        BotUtils.assertGreaterThan(name, number, attributeInAsNumber(name, webElement), webElement);
    }

    public static void assertAttributeGreaterThanOrEquals(String name, double number, WebElement webElement) {
        BotUtils.assertGreaterThanOrEquals(name, number, attributeInAsNumber(name, webElement), webElement);
    }



    /* Id */
    public static void assertIdEquals(String value, WebElement webElement) {
        assertAttributeEquals("id", value, webElement);
    }

    public static void assertIdNotEquals(String value, WebElement webElement) {
        assertAttributeNotEquals("id", value, webElement);
    }

    public static void assertIdContains(String searchText, WebElement webElement) {
        assertAttributeContains("id", searchText, webElement);
    }

    public static void assertIdNotContains(String searchText, WebElement webElement) {
        assertAttributeNotContains("id", searchText, webElement);
    }

    public static void assertIdStartsWith(String prefix, WebElement webElement) {
        assertAttributeStartsWith("id", prefix, webElement);
    }

    public static void assertIdNotStartsWith(String prefix, WebElement webElement) {
        assertAttributeNotStartsWith("id", prefix, webElement);
    }

    public static void assertIdEndsWith(String suffix, WebElement webElement) {
        assertAttributeEndsWith("id", suffix, webElement);
    }

    public static void assertIdNotEndsWith(String suffix, WebElement webElement) {
        assertAttributeNotEndsWith("id", suffix, webElement);
    }

    public static void assertIdMatches(String regExp, WebElement webElement) {
        assertAttributeMatches("id", regExp, webElement);
    }

    public static void assertIdNotMatches(String regExp, WebElement webElement) {
        assertAttributeNotMatches("id", regExp, webElement);
    }



    /* Name */
    public static void assertNameEquals(String value, WebElement webElement) {
        assertAttributeEquals("name", value, webElement);
    }

    public static void assertNameNotEquals(String value, WebElement webElement) {
        assertAttributeNotEquals("name", value, webElement);
    }

    public static void assertNameContains(String searchText, WebElement webElement) {
        assertAttributeContains("name", searchText, webElement);
    }

    public static void assertNameNotContains(String searchText, WebElement webElement) {
        assertAttributeNotContains("name", searchText, webElement);
    }

    public static void assertNameStartsWith(String prefix, WebElement webElement) {
        assertAttributeStartsWith("name", prefix, webElement);
    }

    public static void assertNameNotStartsWith(String prefix, WebElement webElement) {
        assertAttributeNotStartsWith("name", prefix, webElement);
    }

    public static void assertNameEndsWith(String suffix, WebElement webElement) {
        assertAttributeEndsWith("name", suffix, webElement);
    }

    public static void assertNameNotEndsWith(String suffix, WebElement webElement) {
        assertAttributeNotEndsWith("name", suffix, webElement);
    }

    public static void assertNameMatches(String regExp, WebElement webElement) {
        assertAttributeMatches("name", regExp, webElement);
    }

    public static void assertNameNotMatches(String regExp, WebElement webElement) {
        assertAttributeNotMatches("name", regExp, webElement);
    }



    /* Class */
    private static String classIn(WebElement webElement) {
        return attributeIn("class", webElement);
    }

    private static List<String> classesIn(WebElement webElement) {
        return Arrays.asList(StringUtils.split(classIn(webElement)));
    }

    private static boolean hasClass(String className, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.isEqual(className, clazz)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNotClass(String className, WebElement webElement) {
        return !hasClass(className, webElement);
    }

    private static boolean hasClassContaining(String searchText, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.contains(searchText, clazz)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNotClassContaining(String searchText, WebElement webElement) {
        return !hasClassContaining(searchText, webElement);
    }

    private static boolean hasClassStartingWith(String prefix, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.startsWith(prefix, clazz)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNotClassStartingWith(String prefix, WebElement webElement) {
        return !hasClassStartingWith(prefix, webElement);
    }

    private static boolean hasClassEndingWith(String suffix, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.endsWith(suffix, clazz)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNotClassEndingWith(String suffix, WebElement webElement) {
        return !hasClassEndingWith(suffix, webElement);
    }

    private static boolean hasClassMatching(String regExp, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.matches(regExp, clazz)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNotClassMatching(String regExp, WebElement webElement) {
        return !hasClassMatching(regExp, webElement);
    }

    public static void assertHasClass(WebElement webElement) {
        assertHasAttribute("class", webElement);
    }

    public static void assertHasNotClass(WebElement webElement) {
        assertHasNotAttribute("class", webElement);
    }

    public static void assertHasClass(String className, WebElement webElement) {
        if (hasNotClass(className, webElement)) {
            throw new WebDriverAssertionError("Element does not have class " + quote(className.trim()), webElement);
        }
    }

    public static void assertHasNotClass(String className, WebElement webElement) {
        if (hasClass(className, webElement)) {
            throw new WebDriverAssertionError("Element has class " + quote(className.trim()) + " when it shouldn't", webElement);
        }
    }

    public static void assertHasClassContaining(String searchText, WebElement webElement) {
        if (hasNotClassContaining(searchText, webElement)) {
            throw new WebDriverAssertionError("Element does not have class containing text " + quote(searchText.trim()), webElement);
        }
    }

    public static void assertHasNotClassContaining(String searchText, WebElement webElement) {
        if (hasClassContaining(searchText, webElement)) {
            throw new WebDriverAssertionError("Element has class containing text " + quote(searchText.trim()) + " when it shouldn't", webElement);
        }
    }

    public static void assertHasClassStartingWith(String prefix, WebElement webElement) {
        if (hasNotClassStartingWith(prefix, webElement)) {
            throw new WebDriverAssertionError("Element does not have class containing prefix " + quote(prefix.trim()), webElement);
        }
    }

    public static void assertHasNotClassStartingWith(String prefix, WebElement webElement) {
        if (hasClassStartingWith(prefix, webElement)) {
            throw new WebDriverAssertionError("Element has class containing prefix " + quote(prefix.trim()) + " when it shouldn't", webElement);
        }
    }

    public static void assertHasClassEndingWith(String suffix, WebElement webElement) {
        if (hasNotClassEndingWith(suffix, webElement)) {
            throw new WebDriverAssertionError("Element does not have class containing suffix " + quote(suffix.trim()), webElement);
        }
    }

    public static void assertHasNotClassEndingWith(String suffix, WebElement webElement) {
        if (hasClassEndingWith(suffix, webElement)) {
            throw new WebDriverAssertionError("Element has class containing suffix " + quote(suffix.trim()) + " when it shouldn't", webElement);
        }
    }

    public static void assertHasClassMatching(String regExp, WebElement webElement) {
        if (hasNotClassMatching(regExp, webElement)) {
            throw new WebDriverAssertionError("Element does not have class matching regExp " + quote(regExp.trim()), webElement);
        }
    }

    public static void assertHasNotClassMatching(String regExp, WebElement webElement) {
        if (hasClassMatching(regExp, webElement)) {
            throw new WebDriverAssertionError("Element has class matching regExp " + quote(regExp.trim()) + " when it shouldn't", webElement);
        }
    }



    /* Value */
    private static boolean valueEquals(String value, WebElement webElement) {
        return attributeEquals("value", value, webElement);
    }

    public static void assertValueEquals(String value, WebElement webElement) {
        assertAttributeEquals("value", value, webElement);
    }

    public static void assertValueNotEquals(String value, WebElement webElement) {
        assertAttributeNotEquals("value", value, webElement);
    }

    public static void assertValueContains(String searchText, WebElement webElement) {
        assertAttributeContains("value", searchText, webElement);
    }

    public static void assertValueNotContains(String searchText, WebElement webElement) {
        assertAttributeNotContains("value", searchText, webElement);
    }

    public static void assertValueStartsWith(String prefix, WebElement webElement) {
        assertAttributeStartsWith("value", prefix, webElement);
    }

    public static void assertValueNotStartsWith(String prefix, WebElement webElement) {
        assertAttributeNotStartsWith("value", prefix, webElement);
    }

    public static void assertValueEndsWith(String suffix, WebElement webElement) {
        assertAttributeEndsWith("value", suffix, webElement);
    }

    public static void assertValueNotEndsWith(String suffix, WebElement webElement) {
        assertAttributeNotEndsWith("value", suffix, webElement);
    }

    public static void assertValueMatches(String regExp, WebElement webElement) {
        assertAttributeMatches("value", regExp, webElement);
    }

    public static void assertValueNotMatches(String regExp, WebElement webElement) {
        assertAttributeNotMatches("value", regExp, webElement);
    }



    /* Value as Number */
    public static void assertValueIsNumber(WebElement webElement) {
        assertAttributeIsNumber("value", webElement);
    }

    public static void assertValueIsNotNumber(WebElement webElement) {
        assertAttributeIsNotNumber("value", webElement);
    }

    public static void assertValueEquals(double number, WebElement webElement) {
        assertAttributeEquals("value", number, webElement);
    }

    public static void assertValueNotEquals(double number, WebElement webElement) {
        assertAttributeNotEquals("value", number, webElement);
    }

    public static void assertValueLessThan(double number, WebElement webElement) {
        assertAttributeLessThan("value", number, webElement);
    }

    public static void assertValueLessThanOrEquals(double number, WebElement webElement) {
        assertAttributeLessThanOrEquals("value", number, webElement);
    }

    public static void assertValueGreaterThan(double number, WebElement webElement) {
        assertAttributeGreaterThan("value", number, webElement);
    }

    public static void assertValueGreaterThanOrEquals(double number, WebElement webElement) {
        assertAttributeGreaterThanOrEquals("value", number, webElement);
    }



    /* Href */
    public static void assertHrefEquals(String value, WebElement webElement) {
        assertAttributeEquals("href", value, webElement);
    }

    public static void assertHrefNotEquals(String value, WebElement webElement) {
        assertAttributeNotEquals("href", value, webElement);
    }

    public static void assertHrefContains(String searchText, WebElement webElement) {
        assertAttributeContains("href", searchText, webElement);
    }

    public static void assertHrefNotContains(String searchText, WebElement webElement) {
        assertAttributeNotContains("href", searchText, webElement);
    }

    public static void assertHrefStartsWith(String prefix, WebElement webElement) {
        assertAttributeStartsWith("href", prefix, webElement);
    }

    public static void assertHrefNotStartsWith(String prefix, WebElement webElement) {
        assertAttributeNotStartsWith("href", prefix, webElement);
    }

    public static void assertHrefEndsWith(String suffix, WebElement webElement) {
        assertAttributeEndsWith("href", suffix, webElement);
    }

    public static void assertHrefNotEndsWith(String suffix, WebElement webElement) {
        assertAttributeNotEndsWith("href", suffix, webElement);
    }

    public static void assertHrefMatches(String regExp, WebElement webElement) {
        assertAttributeMatches("href", regExp, webElement);
    }

    public static void assertHrefNotMatches(String regExp, WebElement webElement) {
        assertAttributeNotMatches("href", regExp, webElement);
    }



    /* Text */
    private static String textIn(WebElement webElement) {
        // Text is trimmed to normalize behavior since Chrome and PhantomJS driver incorrectly returns spaces around the text (Not according the the WebElement tetText docs), see bug report https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/7473 remove this when bug is solved!
        return StringUtils.trim(webElement.getText());
    }

    private static boolean hasText(WebElement webElement) {
        return BotUtils.notEquals("", textIn(webElement));
    }

    private static boolean hasNotText(WebElement webElement) {
        return BotUtils.isEqual("", textIn(webElement));
    }

    private static boolean textEquals(String text, WebElement webElement) {
        return BotUtils.isEqual(text, textIn(webElement));
    }

    public static void assertHasText(WebElement webElement) {
        if (hasNotText(webElement)) {
            throw new WebDriverAssertionError("Element has no text", webElement);
        }
    }

    public static void assertHasNotText(WebElement webElement) {
        if (hasText(webElement)) {
            throw new WebDriverAssertionError("Element has text " + quote(textIn(webElement)) + " when it shouldn't", webElement);
        }
    }

    public static void assertTextEquals(String text, WebElement webElement) {
        BotUtils.assertEquals("Text", text, textIn(webElement), webElement);
    }

    public static void assertTextNotEquals(String text, WebElement webElement) {
        BotUtils.assertNotEquals("Text", text, textIn(webElement), webElement);
    }

    public static void assertTextEqualsIgnoreCase(String text, WebElement webElement) {
        BotUtils.assertEqualsIgnoreCase("Text", text, textIn(webElement), webElement);
    }

    public static void assertTextNotEqualsIgnoreCase(String text, WebElement webElement) {
        BotUtils.assertNotEqualsIgnoreCase("Text", text, textIn(webElement), webElement);
    }

    public static void assertTextContains(String searchText, WebElement webElement) {
        BotUtils.assertContains("Text", searchText, textIn(webElement), webElement);
    }

    public static void assertTextNotContains(String searchText, WebElement webElement) {
        BotUtils.assertNotContains("Text", searchText, textIn(webElement), webElement);
    }

    public static void assertTextContainsIgnoreCase(String searchText, WebElement webElement) {
        BotUtils.assertContainsIgnoreCase("Text", searchText, textIn(webElement), webElement);
    }

    public static void assertTextNotContainsIgnoreCase(String searchText, WebElement webElement) {
        BotUtils.assertNotContainsIgnoreCase("Text", searchText, textIn(webElement), webElement);
    }

    public static void assertTextStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertStartsWith("Text", prefix, textIn(webElement), webElement);
    }

    public static void assertTextNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertNotStartsWith("Text", prefix, textIn(webElement), webElement);
    }

    public static void assertTextStartsWithIgnoreCase(String prefix, WebElement webElement) {
        BotUtils.assertStartsWithIgnoreCase("Text", prefix, textIn(webElement), webElement);
    }

    public static void assertTextNotStartsWithIgnoreCase(String prefix, WebElement webElement) {
        BotUtils.assertNotStartsWithIgnoreCase("Text", prefix, textIn(webElement), webElement);
    }

    public static void assertTextEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertEndsWith("Text", suffix, textIn(webElement), webElement);
    }

    public static void assertTextNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertNotEndsWith("Text", suffix, textIn(webElement), webElement);
    }

    public static void assertTextEndsWithIgnoreCase(String suffix, WebElement webElement) {
        BotUtils.assertEndsWithIgnoreCase("Text", suffix, textIn(webElement), webElement);
    }

    public static void assertTextNotEndsWithIgnoreCase(String suffix, WebElement webElement) {
        BotUtils.assertNotEndsWithIgnoreCase("Text", suffix, textIn(webElement), webElement);
    }

    public static void assertTextMatches(String regExp, WebElement webElement) {
        BotUtils.assertMatches("Text", regExp, textIn(webElement), webElement);
    }

    public static void assertTextNotMatches(String regExp, WebElement webElement) {
        BotUtils.assertNotMatches("Text", regExp, textIn(webElement), webElement);
    }



    /* Text as Number */
    private static double textInAsNumber(WebElement webElement) {
        return createDouble(textIn(webElement));
    }

    private static boolean textIsNumber(WebElement webElement) {
        try {
            textInAsNumber(webElement);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean textIsNotNumber(WebElement webElement) {
        return !textIsNumber(webElement);
    }

    public static void assertTextIsNumber(WebElement webElement) {
        if (textIsNotNumber(webElement)) {
            throw new WebDriverAssertionError("Element text is not a number", webElement);
        }
    }

    public static void assertTextIsNotNumber(WebElement webElement) {
        if (textIsNumber(webElement)) {
            throw new WebDriverAssertionError("Element text is a number when it shouldn't", webElement);
        }
    }

    public static void assertTextEquals(double number, WebElement webElement) {
        BotUtils.assertEquals("Text", number, textInAsNumber(webElement), webElement);
    }

    public static void assertTextNotEquals(double number, WebElement webElement) {
        BotUtils.assertNotEquals("Text", number, textInAsNumber(webElement), webElement);
    }

    public static void assertTextLessThan(double number, WebElement webElement) {
        BotUtils.assertLessThan("Text", number, textInAsNumber(webElement), webElement);
    }

    public static void assertTextLessThanOrEquals(double number, WebElement webElement) {
        BotUtils.assertLessThanOrEquals("Text", number, textInAsNumber(webElement), webElement);
    }

    public static void assertTextGreaterThan(double number, WebElement webElement) {
        BotUtils.assertGreaterThan("Text", number, textInAsNumber(webElement), webElement);
    }

    public static void assertTextGreaterThanOrEquals(double number, WebElement webElement) {
        BotUtils.assertGreaterThanOrEquals("Text", number, textInAsNumber(webElement), webElement);
    }



    /* Selected/Deselected */
    private static boolean isSelected(WebElement webElement) {
        return webElement.isSelected();
    }

    private static boolean isDeselected(WebElement webElement) {
        return !isSelected(webElement);
    }

    public static void assertIsSelected(WebElement webElement) {
        if (isDeselected(webElement)) {
            throw new WebDriverAssertionError("Element is not selected", webElement);
        }
    }

    public static void assertIsDeselected(WebElement webElement) {
        if (isSelected(webElement)) {
            throw new WebDriverAssertionError("Element is not deselected", webElement);
        }
    }




    /* Checked/Unchecked */
    private static boolean isChecked(WebElement webElement) {
        return webElement.isSelected();
    }

    private static boolean isUnchecked(WebElement webElement) {
        return !isChecked(webElement);
    }

    public static void assertIsChecked(WebElement webElement) {
        if (isUnchecked(webElement)) {
            throw new WebDriverAssertionError("Element is not checked", webElement);
        }
    }

    public static void assertIsUnchecked(WebElement webElement) {
        if (isChecked(webElement)) {
            throw new WebDriverAssertionError("Element is not unchecked", webElement);
        }
    }



    /* Enabled/Disabled */
    private static boolean isEnabled(WebElement webElement) {
        return webElement.isEnabled();
    }

    private static boolean isDisabled(WebElement webElement) {
        return !isEnabled(webElement);
    }

    public static void assertIsEnabled(WebElement webElement) {
        if (isDisabled(webElement)) {
            throw new WebDriverAssertionError("Element is not enabled", webElement);
        }
    }

    public static void assertIsDisabled(WebElement webElement) {
        if (isEnabled(webElement)) {
            throw new WebDriverAssertionError("Element is not disabled", webElement);
        }
    }



    /* Option */
    private static boolean hasOption(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNotOption(String text, WebElement webElement) {
        return !hasOption(text, webElement);
    }

    private static boolean optionIsEnabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    private static boolean optionIsDisabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    private static boolean optionIsSelected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    private static boolean optionIsDeselected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }

    private static boolean allOptionsAreSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isDeselected(option)) {
                return false;
            }
        }
        return true;
    }

    private static boolean noOptionIsSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isSelected(option)) {
                return false;
            }
        }
        return true;
    }

    public static void assertHasOption(String text, WebElement webElement) {
        if (hasNotOption(text, webElement)) {
            throw new WebDriverAssertionError("Element has no option " + quote(text.trim()), webElement);
        }
    }

    public static void assertHasNotOption(String text, WebElement webElement) {
        if (hasOption(text, webElement)) {
            throw new WebDriverAssertionError("Element has option " + quote(text.trim()) + " when it shouldn't", webElement);
        }
    }

    public static void assertOptionIsEnabled(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsDisabled(text, webElement)) {
            throw new WebDriverAssertionError("Option " + quote(text.trim()) + " is not enabled", webElement);
        }
    }

    public static void assertOptionIsDisabled(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsEnabled(text, webElement)) {
            throw new WebDriverAssertionError("Option " + quote(text.trim()) + " is not disabled", webElement);
        }
    }

    public static void assertOptionIsSelected(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsDeselected(text, webElement)) {
            throw new WebDriverAssertionError("Option " + quote(text.trim()) + " is not selected", webElement);
        }
    }

    public static void assertOptionIsDeselected(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsSelected(text, webElement)) {
            throw new WebDriverAssertionError("Option " + quote(text.trim()) + " is not deselected", webElement);
        }
    }

    public static void assertAllOptionsAreSelected(WebElement webElement) {
        if (!allOptionsAreSelected(webElement)) {
            throw new WebDriverAssertionError("All options are not selected", webElement);
        }
    }

    public static void assertNoOptionIsSelected(WebElement webElement) {
        if (!noOptionIsSelected(webElement)) {
            throw new WebDriverAssertionError("All options are not deselected", webElement);
        }
    }



    /* Option Value */
    private static boolean hasOptionWithValue(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNotOptionWithValue(String value, WebElement webElement) {
        return !hasOptionWithValue(value, webElement);
    }

    private static boolean optionWithValueIsEnabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    private static boolean optionWithValueIsDisabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    private static boolean optionWithValueIsSelected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    private static boolean optionWithValueIsDeselected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }

    public static void assertHasOptionWithValue(String value, WebElement webElement) {
        if (hasNotOptionWithValue(value, webElement)) {
            throw new WebDriverAssertionError("Element has no option with value " + quote(value.trim()), webElement);
        }
    }

    public static void assertHasNotOptionWithValue(String value, WebElement webElement) {
        if (hasOptionWithValue(value, webElement)) {
            throw new WebDriverAssertionError("Element has option with value " + quote(value.trim()) + " when it shouldn't", webElement);
        }
    }

    public static void assertOptionWithValueIsEnabled(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsDisabled(value, webElement)) {
            throw new WebDriverAssertionError("Option with value " + quote(value.trim()) + " is not enabled", webElement);
        }
    }

    public static void assertOptionWithValueIsDisabled(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsEnabled(value, webElement)) {
            throw new WebDriverAssertionError("Option with value " + quote(value.trim()) + " is not disabled", webElement);
        }
    }

    public static void assertOptionWithValueIsSelected(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsDeselected(value, webElement)) {
            throw new WebDriverAssertionError("Option with value " + quote(value.trim()) + " is not selected", webElement);
        }
    }

    public static void assertOptionWithValueIsDeselected(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsSelected(value, webElement)) {
            throw new WebDriverAssertionError("Option with value " + quote(value.trim()) + " is not deselected", webElement);
        }
    }



    /* Option Index */
    private static boolean hasOptionWithIndex(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return options.get(index) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean hasNotOptionWithIndex(int index, WebElement webElement) {
        return !hasOptionWithIndex(index, webElement);
    }

    private static boolean optionWithIndexIsEnabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isEnabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean optionWithIndexIsDisabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDisabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean optionWithIndexIsSelected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isSelected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean optionWithIndexIsDeselected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDeselected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static void assertHasOptionWithIndex(int index, WebElement webElement) {
        if (hasNotOptionWithIndex(index, webElement)) {
            throw new WebDriverAssertionError("Element  has no option with index " + quote(index), webElement);
        }
    }

    public static void assertHasNotOptionWithIndex(int index, WebElement webElement) {
        if (hasOptionWithIndex(index, webElement)) {
            throw new WebDriverAssertionError("Element has option with index " + quote(index) + " when it shouldn't", webElement);
        }
    }

    public static void assertOptionWithIndexIsEnabled(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsDisabled(index, webElement)) {
            throw new WebDriverAssertionError("Option with index " + quote(index) + " is not enabled", webElement);
        }
    }

    public static void assertOptionWithIndexIsDisabled(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsEnabled(index, webElement)) {
            throw new WebDriverAssertionError("Option with index " + quote(index) + " is not disabled", webElement);
        }
    }

    public static void assertOptionWithIndexIsSelected(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsDeselected(index, webElement)) {
            throw new WebDriverAssertionError("Option with index " + quote(index) + " is not selected", webElement);
        }
    }

    public static void assertOptionWithIndexIsDeselected(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsSelected(index, webElement)) {
            throw new WebDriverAssertionError("Option with index " + quote(index) + " is not deselected", webElement);
        }
    }
}
