package com.brangelov.seleniumtests.anotepad;

import com.brangelov.seleniumtests.common.SeleniumTest;
import com.brangelov.seleniumtests.pages.ANotepadLandingPage;
import com.brangelov.seleniumtests.pages.ANotepadLoginRegisterPage;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

public class ANotepadLandingPageTests extends SeleniumTest {

    @Test
    public void landingPageLoadsTest() {
        doForEachBrowser((browser, webDriver) -> {
            ANotepadLandingPage landingPage = new ANotepadLandingPage(webDriver, context);
            landingPage.navigate();

            validateElements(browser, landingPage);
        });
    }

    @Test
    public void canNavigateToLoginRegister() {
        doForEachBrowser((browser, webDriver) -> {
            ANotepadLandingPage landingPage = new ANotepadLandingPage(webDriver, context);

            landingPage.navigate();

            ANotepadLoginRegisterPage registerPage = landingPage.moveToLoginRegister();
            Assert.assertEquals(browser + ": Navigated to register page",
                    registerPage.getPageUrl(), webDriver.getCurrentUrl());
        });
    }

    private void validateElements(String browser, ANotepadLandingPage landingPage) {
        Assert.assertNotNull(browser + ": Title input exists", landingPage.getTitleTextbox());
        Assert.assertNotNull(browser + ": Upper save button exists", landingPage.getSaveButton());
        Assert.assertNotNull(browser + ": Note input exists", landingPage.getNoteTextbox());
        Assert.assertNotNull(browser + ": Lower save button exists", landingPage.getLowerSaveButton());
        Assert.assertNotNull(browser + ": Register/Login link exists", landingPage.getLoginRegisterButton());
        Assert.assertNotNull(browser + ": Saved notes exists", landingPage.getSavedNotes());
    }
}
