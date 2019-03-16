package com.brangelov.seleniumtests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

public abstract class ANotepadPage extends WebPage {

    @Value("${tests.values.login-page.url}")
    protected String loginRegisterUrl;

    @FindBy(xpath = "//*[@id='bs-example-navbar-collapse-1']//a[@href='/create_account' or @href='/logout']")
    protected WebElement loginRegisterButton;

    public ANotepadPage(WebDriver driver, ApplicationContext context) {
        super(driver, context);
    }

    public boolean isLoggedIn() {
        return loginRegisterButton.getText().equals(getLogoutBtnText());
    }

    public ANotepadLoginRegisterPage moveToLoginRegister() {
        loginRegisterButton.click();
        sleepUntil(() -> isAtUrl(loginRegisterUrl));
        return new ANotepadLoginRegisterPage(driver, context);
    }

    public ANotepadLandingPage login() {
        if (isLoggedIn()) return new ANotepadLandingPage(driver, context);

        return moveToLoginRegister().doLogin();
    }

    public ANotepadLandingPage logout() {
        if (isLoggedIn()) {
            loginRegisterButton.click();
            sleepUntil(() -> !isLoggedIn());
        }
        return new ANotepadLandingPage(driver, context);
    }

    public String getLoginRegisterBtnText() {
        return "Register/Login";
    }

    public String getLogoutBtnText() {
        return "Logout";
    }

    public WebElement getLoginRegisterButton() {
        return loginRegisterButton;
    }

    public void setLoginRegisterButton(WebElement loginRegisterButton) {
        this.loginRegisterButton = loginRegisterButton;
    }
}
