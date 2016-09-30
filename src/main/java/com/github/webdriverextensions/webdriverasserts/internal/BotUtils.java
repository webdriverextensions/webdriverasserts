package com.github.webdriverextensions.webdriverasserts.internal;

import java.util.concurrent.TimeUnit;

import com.github.webdriverextensions.webdriverasserts.WebDriverAssertionError;
import com.github.webdriverextensions.webdriverasserts.WebDriverAsserts;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class BotUtils {

    private BotUtils() {}

    /* Html */
    public static String htmlOf(WebElement webElement) {
        if (webElement == null) {
            return "Element is null";
        }
        String innerHtml = innerHtmlOf(webElement);
        if (StringUtils.isBlank(innerHtml)) {
            return "<" + tagNameOf(webElement) + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.prependSpaceIfNotBlank(attributesIn(webElement)) + " />";
        }
        return "<" + tagNameOf(webElement) + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.prependSpaceIfNotBlank(attributesIn(webElement)) + ">"
                + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.surroundNewLinesIfContainsNewLine(innerHtml)
                + "</" + tagNameOf(webElement) + ">";

    }

    public static String tagNameOf(WebElement webElement) {
        return webElement.getTagName();
    }

    public static String innerHtmlOf(WebElement webElement) {
        return (String) executeJavascript("return arguments[0].innerHTML;", webElement);
    }



    /* Execute Javascript */
    public static Object executeJavascript(String script, Object... arguments) {
        return ((JavascriptExecutor) WebDriverAsserts.getDriver()).executeScript(script, arguments);
    }



    /* Attributes */
    public static String attributesIn(WebElement webElement) {
        return (String) executeJavascript(
                "var attrsString = '';"
                + "for (var attr, i=0, attrs=arguments[0].attributes, l=attrs.length; i<l; i++) {"
                + "    var attr = attrs.item(i);"
                + "    if (i != 0) {"
                + "        attrsString = attrsString + ' ';"
                + "    }"
                + "    attrsString = attrsString + attr.nodeName + '=\"' + attr.nodeValue + '\"';"
                + "}"
                + "return attrsString;", webElement);
    }



    /* String Equals */
    public static boolean isEqual(String text1, String text2) {
        return StringUtils.equals(text1, text2);
    }

    public static boolean notEquals(String text1, String text2) {
        return !StringUtils.equals(text1, text2);
    }

    public static boolean equalsIgnoreCase(String text1, String text2) {
        return StringUtils.equalsIgnoreCase(text1, text2);
    }

    public static boolean notEqualsIgnoreCase(String text1, String text2) {
        return !StringUtils.equalsIgnoreCase(text1, text2);
    }

    public static boolean contains(String searchText, String text) {
        return StringUtils.contains(text, searchText);
    }

    public static boolean notContains(String searchText, String text) {
        return !StringUtils.contains(text, searchText);
    }

    public static boolean containsIgnoreCase(String searchText, String text) {
        return StringUtils.containsIgnoreCase(text, searchText);
    }

    public static boolean notContainsIgnoreCase(String searchText, String text) {
        return !StringUtils.containsIgnoreCase(text, searchText);
    }

    public static boolean startsWith(String prefix, String text) {
        return StringUtils.startsWith(text, prefix);
    }

    public static boolean notStartsWith(String prefix, String text) {
        return !StringUtils.startsWith(text, prefix);
    }

    public static boolean startsWithIgnoreCase(String prefix, String text) {
        return StringUtils.startsWithIgnoreCase(text, prefix);
    }

    public static boolean notStartsWithIgnoreCase(String prefix, String text) {
        return !StringUtils.startsWithIgnoreCase(text, prefix);
    }

    public static boolean endsWith(String suffix, String text) {
        return StringUtils.endsWith(text, suffix);
    }

    public static boolean notEndsWith(String suffix, String text) {
        return !StringUtils.endsWith(text, suffix);
    }

    public static boolean endsWithIgnoreCase(String suffix, String text) {
        return StringUtils.endsWithIgnoreCase(text, suffix);
    }

    public static boolean notEndsWithIgnoreCase(String suffix, String text) {
        return !StringUtils.endsWithIgnoreCase(text, suffix);
    }

    public static boolean matches(String regularExpression, String text) {
        if (text == null || regularExpression == null) {
            return false;
        }
        return text.matches(regularExpression);
    }

    public static boolean notMatches(String regularExpression, String text) {
        if (text == null || regularExpression == null) {
            return true;
        }
        return !text.matches(regularExpression);
    }

    public static void assertEquals(String name, String expected, String actual) {
        if (notEquals(expected, actual)) {
            throw new WebDriverAssertionError(name + " is not equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(expected), name, actual);
        }

    }

    public static void assertNotEquals(String name, String notExpected, String actual) {
        if (isEqual(notExpected, actual)) {
            throw new WebDriverAssertionError(name + " is equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(notExpected) + " when it shouldn't", name, actual);
        }
    }

    public static void assertMatches(String name, String regExp, String actual) {
        if (notMatches(regExp, actual)) {
            throw new WebDriverAssertionError(name + " is not matching " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(regExp), name, actual);
        }
    }

    public static void assertNotMatches(String name, String regExp, String actual) {
        if (matches(regExp, actual)) {
            throw new WebDriverAssertionError(name + " is matching " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(regExp) + " when it shouldn't", name, actual);
        }
    }

    public static void assertContains(String name, String searchText, String actual) {
        if (notContains(searchText, actual)) {
            throw new WebDriverAssertionError(name + " is not containing " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(searchText), name, actual);
        }
    }

    public static void assertNotContains(String name, String searchText, String actual) {
        if (contains(searchText, actual)) {
            throw new WebDriverAssertionError(name + " is containing " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(searchText) + " when it shouldn't", name, actual);
        }
    }

    public static void assertStartsWith(String name, String prefix, String actual) {
        if (notStartsWith(prefix, actual)) {
            throw new WebDriverAssertionError(name + " is not starting with " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(prefix), name, actual);
        }
    }

    public static void assertNotStartsWith(String name, String prefix, String actual) {
        if (startsWith(prefix, actual)) {
            throw new WebDriverAssertionError(name + " is starting with " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(prefix) + " when it shouldn't", name, actual);
        }
    }

    public static void assertEndsWith(String name, String suffix, String actual) {
        if (notEndsWith(suffix, actual)) {
            throw new WebDriverAssertionError(name + " is not ending with " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(suffix), name, actual);
        }
    }

    public static void assertNotEndsWith(String name, String suffix, String actual) {
        if (endsWith(suffix, actual)) {
            throw new WebDriverAssertionError(name + " is ending with " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(suffix) + " when it shouldn't", name, actual);
        }
    }

    public static void assertEquals(String name, String expected, String actual, WebElement webElement) {
        if (notEquals(expected, actual)) {
            throw new WebDriverAssertionError(name + " is not equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(expected), webElement);
        }
    }

    public static void assertNotEquals(String name, String notExpected, String actual, WebElement webElement) {
        if (isEqual(notExpected, actual)) {
            throw new WebDriverAssertionError(name + " is equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(notExpected) + " when it shouldn't", webElement);
        }
    }

    public static void assertEqualsIgnoreCase(String name, String expected, String actual, WebElement webElement) {
        if (notEqualsIgnoreCase(expected, actual)) {
            throw new WebDriverAssertionError(name + " is not equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(expected), webElement);
        }
    }

    public static void assertNotEqualsIgnoreCase(String name, String notExpected, String actual, WebElement webElement) {
        if (equalsIgnoreCase(notExpected, actual)) {
            throw new WebDriverAssertionError(name + " is equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(notExpected) + " when it shouldn't", webElement);
        }
    }

    public static void assertMatches(String name, String regExp, String actual, WebElement webElement) {
        if (notMatches(regExp, actual)) {
            throw new WebDriverAssertionError(name + " is not matching " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(regExp), webElement);
        }
    }

    public static void assertNotMatches(String name, String regExp, String actual, WebElement webElement) {
        if (matches(regExp, actual)) {
            throw new WebDriverAssertionError(name + " is matching " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(regExp) + " when it shouldn't", webElement);
        }
    }

    public static void assertContains(String name, String searchText, String actual, WebElement webElement) {
        if (notContains(searchText, actual)) {
            throw new WebDriverAssertionError(name + " is not containing " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(searchText), webElement);
        }
    }

    public static void assertNotContains(String name, String searchText, String actual, WebElement webElement) {
        if (contains(searchText, actual)) {
            throw new WebDriverAssertionError(name + " is containing " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(searchText) + " when it shouldn't", webElement);
        }
    }

    public static void assertContainsIgnoreCase(String name, String searchText, String actual, WebElement webElement) {
        if (notContainsIgnoreCase(searchText, actual)) {
            throw new WebDriverAssertionError(name + " is not containing " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(searchText), webElement);
        }
    }

    public static void assertNotContainsIgnoreCase(String name, String searchText, String actual, WebElement webElement) {
        if (containsIgnoreCase(searchText, actual)) {
            throw new WebDriverAssertionError(name + " is containing " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(searchText) + " when it shouldn't", webElement);
        }
    }

    public static void assertStartsWith(String name, String prefix, String actual, WebElement webElement) {
        if (notStartsWith(prefix, actual)) {
            throw new WebDriverAssertionError(name + " is not starting with " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(prefix), webElement);
        }
    }

    public static void assertNotStartsWith(String name, String prefix, String actual, WebElement webElement) {
        if (startsWith(prefix, actual)) {
            throw new WebDriverAssertionError(name + " is starting with " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(prefix) + " when it shouldn't", webElement);
        }
    }

    public static void assertStartsWithIgnoreCase(String name, String prefix, String actual, WebElement webElement) {
        if (notStartsWithIgnoreCase(prefix, actual)) {
            throw new WebDriverAssertionError(name + " is not starting with " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(prefix), webElement);
        }
    }

    public static void assertNotStartsWithIgnoreCase(String name, String prefix, String actual, WebElement webElement) {
        if (startsWithIgnoreCase(prefix, actual)) {
            throw new WebDriverAssertionError(name + " is starting with " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(prefix) + " when it shouldn't", webElement);
        }
    }

    public static void assertEndsWith(String name, String suffix, String actual, WebElement webElement) {
        if (notEndsWith(suffix, actual)) {
            throw new WebDriverAssertionError(name + " is not ending with " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(suffix), webElement);
        }
    }

    public static void assertNotEndsWith(String name, String suffix, String actual, WebElement webElement) {
        if (endsWith(suffix, actual)) {
            throw new WebDriverAssertionError(name + " is ending with " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(suffix) + " when it shouldn't", webElement);
        }
    }

    public static void assertEndsWithIgnoreCase(String name, String suffix, String actual, WebElement webElement) {
        if (notEndsWithIgnoreCase(suffix, actual)) {
            throw new WebDriverAssertionError(name + " is not ending with " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(suffix), webElement);
        }
    }

    public static void assertNotEndsWithIgnoreCase(String name, String suffix, String actual, WebElement webElement) {
        if (endsWithIgnoreCase(suffix, actual)) {
            throw new WebDriverAssertionError(name + " is ending with " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(suffix) + " when it shouldn't", webElement);
        }
    }



    /* Double Equals */
    public static boolean isEqual(double number, double actual) {
        return actual == number;
    }

    public static boolean notEquals(double number, double actual) {
        return actual != number;
    }

    public static boolean lessThan(double number, double actual) {
        return actual < number;
    }

    public static boolean lessThanOrEquals(double number, double actual) {
        return actual <= number;
    }

    public static boolean greaterThan(double number, double actual) {
        return actual > number;
    }

    public static boolean greaterThanOrEquals(double number, double actual) {
        return actual >= number;
    }

    public static void assertEquals(String name, double number, double actual) {
        if (notEquals(number, actual)) {
            throw new WebDriverAssertionError(name + " is not equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(number), name, actual);
        }
    }

    public static void assertNotEquals(String name, double number, double actual) {
        if (isEqual(number, actual)) {
            throw new WebDriverAssertionError(name + " is equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(number) + " when it shouldn't", name, actual);
        }
    }

    public static void assertLessThan(String name, double number, double actual) {
        if (greaterThanOrEquals(number, actual)) {
            throw new WebDriverAssertionError(name + " is not less than " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(number), name, actual);
        }
    }

    public static void assertLessThanOrEquals(String name, double number, double actual) {
        if (greaterThan(number, actual)) {
            throw new WebDriverAssertionError(name + " is not less than or equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(number), name, actual);
        }
    }

    public static void assertGreaterThan(String name, double number, double actual) {
        if (lessThanOrEquals(number, actual)) {
            throw new WebDriverAssertionError(name + " is not greater than " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(number), name, actual);
        }
    }

    public static void assertGreaterThanOrEquals(String name, double number, double actual) {
        if (lessThan(number, actual)) {
            throw new WebDriverAssertionError(name + " is not greater than or equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(number), name, actual);
        }
    }

    public static void assertEquals(String name, double number, double actual, WebElement webElement) {
        if (notEquals(number, actual)) {
            throw new WebDriverAssertionError(name + " is not equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(number), webElement);
        }
    }

    public static void assertNotEquals(String name, double number, double actual, WebElement webElement) {
        if (isEqual(number, actual)) {
            throw new WebDriverAssertionError(name + " is equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(number) + " when it shouldn't", webElement);
        }
    }

    public static void assertLessThan(String name, double number, double actual, WebElement webElement) {
        if (greaterThanOrEquals(number, actual)) {
            throw new WebDriverAssertionError(name + " is not less than " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(number), webElement);
        }
    }

    public static void assertLessThanOrEquals(String name, double number, double actual, WebElement webElement) {
        if (greaterThan(number, actual)) {
            throw new WebDriverAssertionError(name + " is not less than or equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(number), webElement);
        }
    }

    public static void assertGreaterThan(String name, double number, double actual, WebElement webElement) {
        if (lessThanOrEquals(number, actual)) {
            throw new WebDriverAssertionError(name + " is not greater than " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(number), webElement);
        }
    }

    public static void assertGreaterThanOrEquals(String name, double number, double actual, WebElement webElement) {
        if (lessThan(number, actual)) {
            throw new WebDriverAssertionError(name + " is not greater than or equal to " + com.github.webdriverextensions.webdriverasserts.internal.StringUtils.quote(number), webElement);
        }
    }

    public static long asNanos(double time, TimeUnit unit) {
        long nanos = 0;
        switch (unit) {
            case DAYS:
                nanos = (long) (time * 24 * 60 * 60 * 1000000000);
                break;
            case HOURS:
                nanos = (long) (time * 60 * 60 * 1000000000);
                break;
            case MINUTES:
                nanos = (long) (time * 60 * 1000000000);
                break;
            case SECONDS:
                nanos = (long) (time * 1000000000);
                break;
            case MILLISECONDS:
                nanos = (long) (time * 1000000);
                break;
            case MICROSECONDS:
                nanos = (long) (time * 1000);
                break;
            case NANOSECONDS:
                nanos = (long) (time);
                break;
        }
        return nanos;
    }
}
