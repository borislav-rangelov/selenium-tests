package com.brangelov.seleniumtests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ANotepadLandingPage extends ANotepadPage {

    @Value("${tests.values.landing-page.url}")
    private String landingPageUrl;

    @FindBy(xpath = "//*[@id='note_form']//div[@class='form-group'][2]//*[contains(@class, 'btn-primary')]")
    private WebElement saveButton;

    @FindBy(id = "edit_title")
    private WebElement titleTextbox;

    @FindBy(id = "edit_textarea")
    private WebElement noteTextbox;

    @FindBy(xpath = "//*[@id='note_form']//div[@class='form-group'][4]//*[contains(@class, 'btn-primary')]")
    private WebElement lowerSaveButton;

    @FindBy(id = "savedNotes")
    private WebElement savedNotes;

    @FindBy(id = "notenumber")
    private WebElement noteNumber;

    public ANotepadLandingPage(WebDriver driver, ApplicationContext context) {
        super(driver, context);
    }

    public ANotepadLandingPage navigate() {
        driver.navigate().to(landingPageUrl);
        return this;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(WebElement saveButton) {
        this.saveButton = saveButton;
    }

    public WebElement getTitleTextbox() {
        return titleTextbox;
    }

    public void setTitleTextbox(WebElement titleTextbox) {
        this.titleTextbox = titleTextbox;
    }

    public WebElement getNoteTextbox() {
        return noteTextbox;
    }

    public void setNoteTextbox(WebElement noteTextbox) {
        this.noteTextbox = noteTextbox;
    }

    public WebElement getLowerSaveButton() {
        return lowerSaveButton;
    }

    public void setLowerSaveButton(WebElement lowerSaveButton) {
        this.lowerSaveButton = lowerSaveButton;
    }

    public WebElement getSavedNotes() {
        return savedNotes;
    }

    public void setSavedNotes(WebElement savedNotes) {
        this.savedNotes = savedNotes;
    }

    public WebElement getNoteNumber() {
        return noteNumber;
    }

    public void setNoteNumber(WebElement noteNumber) {
        this.noteNumber = noteNumber;
    }

    public List<WebElement> getAllSavedNotes() {
        return driver.findElements(By.xpath("//*[@id='savedNotes']/li/a[2]"));
    }

    public WebElement getSavedNote(String noteName) {
        List<WebElement> elements = getAllSavedNotes();
        noteName = noteName.trim();

        for (WebElement element : elements) {
            String title = element.getAttribute("title").split("\\n")[1].trim();
            if (title.endsWith(noteName)) {
                return element;
            }
        }

        return null;
    }

    public WebElement getDeleteButton() {
        return driver.findElement(By.xpath("//*[@id='edit_content']//*[@class='topLinks']//*[@class='delete']"));
    }
}
