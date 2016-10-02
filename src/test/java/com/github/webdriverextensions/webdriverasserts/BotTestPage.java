package com.github.webdriverextensions.webdriverasserts;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BotTestPage {

    public final String url = "file://" + getClass().getResource("/html/bot-test.html").getPath();

    @FindBy(css = "body")
    public WebElement body;

    @FindBy(css = "#test0")
    public WebElement test0;
    @FindBy(css = "#test1")
    public WebElement test1;
    @FindBy(css = "#test2")
    public WebElement test2;
    @FindBy(css = "#test3")
    public WebElement test3;

    // Texts
    @FindBy(css = "#text-span")
    public WebElement textSpan;
    @FindBy(css = "#text-input")
    public WebElement textInput;

    // Integers
    @FindBy(css = "#intnumber-span")
    public WebElement intNumberSpan;
    @FindBy(css = "#intnumber-input")
    public WebElement intNumberInput;

    // Floats
    @FindBy(css = "#floatnumber-span")
    public WebElement floatNumberSpan;
    @FindBy(css = "#floatnumber-input")
    public WebElement floatNumberInput;

    // Select/Options
    @FindBy(css = "#select")
    public WebElement select;
    @FindBy(css = "#select option[value='option1value']")
    public WebElement selectOption1;
    @FindBy(css = "#select option[value='option2value']")
    public WebElement selectOption2;
    @FindBy(css = "#select option[value='option3value']")
    public WebElement selectOption3;
    @FindBy(css = "#select option")
    public List<WebElement> selectAllOption;

    @FindBy(css = "#multiple-select")
    public WebElement multipleSelect;
    @FindBy(css = "#multiple-select option[value='option1value']")
    public WebElement multipleSelectOption1;
    @FindBy(css = "#multiple-select option[value='option2value']")
    public WebElement multipleSelectOption2;
    @FindBy(css = "#multiple-select option[value='option3value']")
    public WebElement multipleSelectOption3;

    // Checkboxes
    @FindBy(css = "#checkbox1")
    public WebElement checkbox1;
    @FindBy(css = "#checkbox2")
    public WebElement checkbox2;

    // Radiobuttons
    @FindBy(css = "#radiobutton1")
    public WebElement radiobutton1;
    @FindBy(css = "#radiobutton2")
    public WebElement radiobutton2;

    // Attributes
    @FindBy(css = "#prefixidsuffix")
    public WebElement attributesSpan;

    // Appended Span
    @FindBy(css = "#firstappended-span")
    public WebElement firstAppendedSpan;
    @FindBy(css = "#secondappended-span")
    public WebElement secondAppendedSpan;
    @FindBy(css = ".appended-span")
    public List<WebElement> appendedSpans;
}
