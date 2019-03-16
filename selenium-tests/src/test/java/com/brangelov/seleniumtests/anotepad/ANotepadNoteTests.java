package com.brangelov.seleniumtests.anotepad;

import com.brangelov.seleniumtests.common.SeleniumTest;
import com.brangelov.seleniumtests.pages.ANotepadLandingPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ANotepadNoteTests extends SeleniumTest {

    @Test
    public void createsNote() {
        doForEachBrowser((browser, webDriver) -> {

            final ANotepadLandingPage landingPage =
                    new ANotepadLandingPage(webDriver, context).navigate().login();

            String noteTitle = "Note title - " + browser;
            String note = "The note";

            landingPage.getTitleTextbox().sendKeys(noteTitle);
            landingPage.getNoteTextbox().sendKeys(note);
            landingPage.getSaveButton().click();

            landingPage.sleepUntil(() -> landingPage.getSavedNote(noteTitle) != null);

            WebElement savedNote = landingPage.getSavedNote(noteTitle);

            Assert.assertNotNull(browser + ": Note saved", savedNote);
            Assert.assertEquals(browser + ": Note title correct",
                    noteTitle, landingPage.getTitleTextbox().getAttribute("value"));
            Assert.assertEquals(browser + ": Note correct",
                    note, landingPage.getNoteTextbox().getAttribute("value"));

            String newTitle = noteTitle + " edited";
            String newNote = note + " edited";
            landingPage.getTitleTextbox().clear();
            landingPage.getTitleTextbox().sendKeys(newTitle);
            landingPage.getNoteTextbox().clear();
            landingPage.getNoteTextbox().sendKeys(newNote);

            landingPage.getSaveButton().click();

            landingPage.sleepUntil(() ->
                    landingPage.getSavedNote(newTitle) != null);

            savedNote = landingPage.getSavedNote(newTitle);
            Assert.assertNotNull(browser + ": Note saved", savedNote);
            Assert.assertEquals(browser + ": Note title correct",
                    newTitle, landingPage.getTitleTextbox().getAttribute("value"));
            Assert.assertEquals(browser + ": Note correct",
                    newNote, landingPage.getNoteTextbox().getAttribute("value"));

            landingPage.getDeleteButton().click();
            landingPage.sleep();

            webDriver.switchTo().alert().accept();
            landingPage.sleepUntil(() -> landingPage.getSavedNote(newTitle) == null);

            savedNote = landingPage.getSavedNote(newTitle);
            Assert.assertNull(browser + ": Note removed", savedNote);
        });
    }
}
