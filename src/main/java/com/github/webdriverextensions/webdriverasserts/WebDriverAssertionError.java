package com.github.webdriverextensions.webdriverasserts;

import static com.github.webdriverextensions.webdriverasserts.internal.BotUtils.htmlOf;

import com.github.webdriverextensions.webdriverasserts.internal.NumberUtils;
import com.github.webdriverextensions.webdriverasserts.internal.StringUtils;
import org.openqa.selenium.WebElement;

public class WebDriverAssertionError extends java.lang.AssertionError {

    private static final String INDENT = "    ";

    public WebDriverAssertionError(String detailMessage, WebElement webElement) {
        super(detailMessage + (webElement != null ? StringUtils.indent("\nElement: " + htmlOf(webElement), INDENT) : ""));
    }

    public WebDriverAssertionError(String detailMessage, String name, String value) {
        super(detailMessage + StringUtils.indent("\n" + name + ": " + value, INDENT));
    }

    public WebDriverAssertionError(String detailMessage, String name, double actual) {
        super(detailMessage + StringUtils.indent("\n" + name + ": " + NumberUtils.toString(actual), INDENT));
    }

}
