package com.github.webdriverextensions.webdriverasserts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static com.github.webdriverextensions.webdriverasserts.WebDriverAsserts.*;

public class WebDriverAssertsTest {

    BotTestPage botTestPage;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/anders/Workspace/webdriverasserts/drivers/chromedriver-mac-32bit");
        ChromeDriver driver = new ChromeDriver();
        WebDriverAsserts.setDriver(driver);
        botTestPage = PageFactory.initElements(driver, BotTestPage.class);
        driver.get(this.botTestPage.url);
    }

    @After
    public void tearDown() throws Exception {
        WebDriverAsserts.getDriver().quit();
        WebDriverAsserts.removeDriver();
    }

    /* Is Display */
    @Test
    public void isDisplayedTest() {
        assertIsDisplayed(botTestPage.textSpan);
        assertIsDisplayed(botTestPage.firstAppendedSpan, 2);
        assertIsNotDisplayed(botTestPage.secondAppendedSpan);
        assertIsDisplayed(botTestPage.secondAppendedSpan, 2);
    }

    /* Size */
    @Test
    public void sizeTest() {
        assertSizeEquals(3, botTestPage.selectAllOption);
        assertSizeNotEquals(0, botTestPage.selectAllOption);
        assertSizeLessThan(4, botTestPage.selectAllOption);
        assertSizeLessThanOrEquals(3, botTestPage.selectAllOption);
        assertSizeGreaterThan(2, botTestPage.selectAllOption);
        assertSizeGreaterThanOrEquals(3, botTestPage.selectAllOption);
    }

    /* Url */
    @Test
    public void urlTest() {
        assertCurrentUrlEquals(botTestPage.url);
        assertCurrentUrlNotEquals("xxx");
        assertCurrentUrlContains("bot-test");
        assertCurrentUrlNotContains("xxx");
        assertCurrentUrlStartsWith("file:/");
        assertCurrentUrlNotStartsWith("xxx");
        assertCurrentUrlEndsWith("/bot-test.html");
        assertCurrentUrlNotEndsWith("xxx");
        assertCurrentUrlMatches(".*bot-test.*");
        assertCurrentUrlNotMatches(".*xxx.*");
    }

    /* Title */
    @Test
    public void titleTest() {
        assertTitleEquals("prefixtitlesuffix");
        assertTitleNotEquals("xxx");
        assertTitleContains("title");
        assertTitleNotContains("xxx");
        assertTitleStartsWith("prefixtitle");
        assertTitleNotStartsWith("xxx");
        assertTitleEndsWith("titlesuffix");
        assertTitleNotEndsWith("xxx");
        assertTitleMatches(".*title.*");
        assertTitleNotMatches(".*xxx.*");
    }

    /* Tag Name */
    @Test
    public void tagNameTest() {
        assertTagNameEquals("span", botTestPage.attributesSpan);
        assertTagNameNotEquals("xxx", botTestPage.attributesSpan);
    }

    /* Attribute */
    @Test
    public void attributeTest() {
        assertAttributeEquals("id", "prefixidsuffix", botTestPage.attributesSpan);
        assertAttributeNotEquals("id", "xxx", botTestPage.attributesSpan);
        assertAttributeContains("id", "id", botTestPage.attributesSpan);
        assertAttributeNotContains("id", "xxx", botTestPage.attributesSpan);
        assertAttributeStartsWith("id", "prefixid", botTestPage.attributesSpan);
        assertAttributeNotStartsWith("id", "xxx", botTestPage.attributesSpan);
        assertAttributeEndsWith("id", "idsuffix", botTestPage.attributesSpan);
        assertAttributeNotEndsWith("id", "xxx", botTestPage.attributesSpan);
        assertAttributeMatches("id", ".*id.*", botTestPage.attributesSpan);
        assertAttributeNotMatches("id", ".*xxx.*", botTestPage.attributesSpan);
    }

    /* Id */
    @Test
    public void idTest() {
        assertIdEquals("prefixidsuffix", botTestPage.attributesSpan);
        assertIdNotEquals("xxx", botTestPage.attributesSpan);
        assertIdContains("id", botTestPage.attributesSpan);
        assertIdNotContains("xxx", botTestPage.attributesSpan);
        assertIdStartsWith("prefixid", botTestPage.attributesSpan);
        assertIdNotStartsWith("xxx", botTestPage.attributesSpan);
        assertIdEndsWith("idsuffix", botTestPage.attributesSpan);
        assertIdNotEndsWith("xxx", botTestPage.attributesSpan);
        assertIdMatches(".*id.*", botTestPage.attributesSpan);
        assertIdNotMatches(".*xxx.*", botTestPage.attributesSpan);
    }

    /* Name */
    @Test
    public void nameTest() {
        assertNameEquals("prefixnamesuffix", botTestPage.attributesSpan);
        assertNameNotEquals("xxx", botTestPage.attributesSpan);
        assertNameContains("name", botTestPage.attributesSpan);
        assertNameNotContains("xxx", botTestPage.attributesSpan);
        assertNameStartsWith("prefixname", botTestPage.attributesSpan);
        assertNameNotStartsWith("xxx", botTestPage.attributesSpan);
        assertNameEndsWith("namesuffix", botTestPage.attributesSpan);
        assertNameNotEndsWith("xxx", botTestPage.attributesSpan);
        assertNameMatches(".*name.*", botTestPage.attributesSpan);
        assertNameNotMatches(".*xxx.*", botTestPage.attributesSpan);
    }

    /* Class */
    @Test
    public void classTest() {
        assertHasClass("prefixclass1suffix", botTestPage.attributesSpan);
        assertHasClass("prefixclass2suffix", botTestPage.attributesSpan);
        assertHasClass("prefixclass3suffix", botTestPage.attributesSpan);
    }

    /* Value */
    @Test
    public void valueTest() {
        assertValueEquals("prefixvaluesuffix", botTestPage.attributesSpan);
        assertValueNotEquals("xxx", botTestPage.attributesSpan);
        assertValueContains("value", botTestPage.attributesSpan);
        assertValueNotContains("xxx", botTestPage.attributesSpan);
        assertValueStartsWith("prefixvalue", botTestPage.attributesSpan);
        assertValueNotStartsWith("xxx", botTestPage.attributesSpan);
        assertValueEndsWith("valuesuffix", botTestPage.attributesSpan);
        assertValueNotEndsWith("xxx", botTestPage.attributesSpan);
        assertValueMatches(".*value.*", botTestPage.attributesSpan);
        assertValueNotMatches(".*xxx.*", botTestPage.attributesSpan);
    }

    /* Value Number */
    @Test
    public void valueNumberTest() {
        // floatNumberInput
        assertValueIsNumber(botTestPage.floatNumberInput);
        assertValueIsNotNumber(botTestPage.textInput);
        assertValueEquals(42.0, botTestPage.floatNumberInput);
        assertValueNotEquals(43.0, botTestPage.floatNumberInput);
        assertValueLessThan(43.0, botTestPage.floatNumberInput);
        assertValueLessThanOrEquals(42.0, botTestPage.floatNumberInput);
        assertValueGreaterThan(41.0, botTestPage.floatNumberInput);
        assertValueGreaterThanOrEquals(42.0, botTestPage.floatNumberInput);

        // intNumberInput
        assertValueIsNumber(botTestPage.intNumberInput);
        assertValueIsNotNumber(botTestPage.textInput);
        assertValueEquals(42.0, botTestPage.intNumberInput);
        assertValueNotEquals(43.0, botTestPage.intNumberInput);
        assertValueLessThan(43.0, botTestPage.intNumberInput);
        assertValueLessThanOrEquals(42.0, botTestPage.intNumberInput);
        assertValueGreaterThan(41.0, botTestPage.intNumberInput);
        assertValueGreaterThanOrEquals(42.0, botTestPage.intNumberInput);
    }

    /* Href */
    @Test
    public void hrefTest() {
        assertHrefEquals("prefixhrefsuffix", botTestPage.attributesSpan);
        assertHrefNotEquals("xxx", botTestPage.attributesSpan);
        assertHrefContains("href", botTestPage.attributesSpan);
        assertHrefNotContains("xxx", botTestPage.attributesSpan);
        assertHrefStartsWith("prefixhref", botTestPage.attributesSpan);
        assertHrefNotStartsWith("xxx", botTestPage.attributesSpan);
        assertHrefEndsWith("hrefsuffix", botTestPage.attributesSpan);
        assertHrefNotEndsWith("xxx", botTestPage.attributesSpan);
        assertHrefMatches(".*href.*", botTestPage.attributesSpan);
        assertHrefNotMatches(".*xxx.*", botTestPage.attributesSpan);
    }

    /* Text */
    @Test
    public void textTest() {
        assertTextEquals("prefixtextsuffix", botTestPage.textSpan);
        assertTextNotEquals("xxx", botTestPage.textSpan);
        assertTextEqualsIgnoreCase("PREFIXTEXTSUFFIX", botTestPage.textSpan);
        assertTextNotEqualsIgnoreCase("xxx", botTestPage.textSpan);
        assertTextContains("text", botTestPage.textSpan);
        assertTextNotContains("xxx", botTestPage.textSpan);
        assertTextContainsIgnoreCase("TEXT", botTestPage.textSpan);
        assertTextNotContainsIgnoreCase("xxx", botTestPage.textSpan);
        assertTextStartsWith("prefixtext", botTestPage.textSpan);
        assertTextNotStartsWith("xxx", botTestPage.textSpan);
        assertTextStartsWithIgnoreCase("PREFIXTEXT", botTestPage.textSpan);
        assertTextNotStartsWithIgnoreCase("xxx", botTestPage.textSpan);
        assertTextEndsWith("textsuffix", botTestPage.textSpan);
        assertTextNotEndsWith("xxx", botTestPage.textSpan);
        assertTextEndsWithIgnoreCase("TEXTSUFFIX", botTestPage.textSpan);
        assertTextNotEndsWithIgnoreCase("xxx", botTestPage.textSpan);
        assertTextMatches(".*text.*", botTestPage.textSpan);
        assertTextNotMatches(".*xxx.*", botTestPage.textSpan);
    }

    /* Text Number */
    @Test
    public void textNumberTest() {
        // floatNumberSpan
        assertTextIsNumber(botTestPage.floatNumberSpan);
        assertTextIsNotNumber(botTestPage.textSpan);
        assertTextEquals(42.0, botTestPage.floatNumberSpan);
        assertTextNotEquals(43.0, botTestPage.floatNumberSpan);
        assertTextLessThan(43.0, botTestPage.floatNumberSpan);
        assertTextLessThanOrEquals(42.0, botTestPage.floatNumberSpan);
        assertTextGreaterThan(41.0, botTestPage.floatNumberSpan);
        assertTextGreaterThanOrEquals(42.0, botTestPage.floatNumberSpan);

        // intNumberSpan
        assertTextIsNumber(botTestPage.intNumberSpan);
        assertTextIsNotNumber(botTestPage.textSpan);
        assertTextEquals(42.0, botTestPage.intNumberSpan);
        assertTextNotEquals(43.0, botTestPage.intNumberSpan);
        assertTextLessThan(43.0, botTestPage.intNumberSpan);
        assertTextLessThanOrEquals(42.0, botTestPage.intNumberSpan);
        assertTextGreaterThan(41.0, botTestPage.intNumberSpan);
        assertTextGreaterThanOrEquals(42.0, botTestPage.intNumberSpan);
    }

    /* Selected/Deselected */
    @Test
    public void selectedDeselectedTest() {
        assertIsSelected(botTestPage.selectOption1);
        assertIsDeselected(botTestPage.selectOption2);
    }

    /* Checked/Unchecked */
    @Test
    public void checkedUncheckedTest() {
        // checkboxes
        assertIsChecked(botTestPage.checkbox1);
        assertIsUnchecked(botTestPage.checkbox2);

        // radiobuttons
        assertIsChecked(botTestPage.radiobutton1);
        assertIsUnchecked(botTestPage.radiobutton2);
    }

    /* Enabled/Disabled */
    @Test
    public void enabledDisabledTest() {
        assertIsEnabled(botTestPage.selectOption1);
        assertIsEnabled(botTestPage.selectOption2);
        assertIsDisabled(botTestPage.selectOption3);
    }

    /* Option */
    @Test
    public void optionTest() {
        // Selected/Deselected
        assertOptionIsSelected("Option 1", botTestPage.select);
        assertOptionIsDeselected("Option 2", botTestPage.select);
        assertOptionIsDeselected("Option 3", botTestPage.select);

        // Enabled/Disabled
        assertOptionIsEnabled("Option 1", botTestPage.select);
        assertOptionIsEnabled("Option 2", botTestPage.select);
        assertOptionIsDisabled("Option 3", botTestPage.select);
    }

    /* Option Value */
    @Test
    public void optionValueTest() {
        // Selected/Deselected
        assertOptionWithValueIsSelected("option1value", botTestPage.select);
        assertOptionWithValueIsDeselected("option2value", botTestPage.select);
        assertOptionWithValueIsDeselected("option3value", botTestPage.select);

        // Enabled/Disabled
        assertOptionWithValueIsEnabled("option1value", botTestPage.select);
        assertOptionWithValueIsEnabled("option2value", botTestPage.select);
        assertOptionWithValueIsDisabled("option3value", botTestPage.select);
    }

    /* Option Index */
    @Test
    public void optionIndexTest() {
        // Selected/Deselected
        assertOptionWithIndexIsSelected(0, botTestPage.select);
        assertOptionWithIndexIsDeselected(1, botTestPage.select);
        assertOptionWithIndexIsDeselected(2, botTestPage.select);

        // Enabled/Disabled
        assertOptionWithIndexIsEnabled(0, botTestPage.select);
        assertOptionWithIndexIsEnabled(1, botTestPage.select);
        assertOptionWithIndexIsDisabled(2, botTestPage.select);
    }
}
