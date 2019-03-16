package com.brangelov.seleniumtests.anotepad;

import com.brangelov.seleniumtests.common.SeleniumTest;
import com.brangelov.seleniumtests.pages.ANotepadLandingPage;
import com.brangelov.seleniumtests.pages.ANotepadLoginRegisterPage;
import org.junit.Assert;
import org.junit.Test;

public class ANotepadLoginPageTests extends SeleniumTest {

    @Test
    public void loginPageLoads() {

        doForEachBrowser((browser, webDriver) -> {
            ANotepadLandingPage landingPage = new ANotepadLandingPage(webDriver, context);

            landingPage.navigate();

            ANotepadLoginRegisterPage registerPage = landingPage.moveToLoginRegister();

            validateLoginRegister(browser, registerPage);
        });
    }

    @Test
    public void logsInAndLogsOut() {
        doForEachBrowser((browser, webDriver) -> {
            ANotepadLandingPage landingPage = new ANotepadLandingPage(webDriver, context);
            landingPage.navigate();

            ANotepadLoginRegisterPage registerPage = landingPage.moveToLoginRegister();
            landingPage = registerPage.login();

            Assert.assertTrue("Has Logged in", landingPage.isLoggedIn());

            landingPage = landingPage.logout();
            Assert.assertFalse("Has Logged out", landingPage.isLoggedIn());
        });
    }

    private void validateLoginRegister(String browser, ANotepadLoginRegisterPage registerPage) {
        Assert.assertNotNull(browser + ": Login email input exists", registerPage.getLoginEmailInput());
        Assert.assertNotNull(browser + ": Login password input exists", registerPage.getLoginPasswordInput());
        Assert.assertNotNull(browser + ": Login submit exists", registerPage.getLoginSubmitButton());
        Assert.assertNotNull(browser + ": Register email input exists", registerPage.getRegisterEmailInput());
        Assert.assertNotNull(browser + ": Register password input exists", registerPage.getRegisterPasswordInput());
        Assert.assertNotNull(browser + ": Register submit exists", registerPage.getRegisterSubmitButton());
    }
}
