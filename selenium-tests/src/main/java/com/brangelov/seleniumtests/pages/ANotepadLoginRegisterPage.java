package com.brangelov.seleniumtests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

public class ANotepadLoginRegisterPage extends ANotepadPage {

    @FindBy(xpath = "//*[@id='create_login']/div[1]//h1")
    private String registerTitle;

    @FindBy(xpath = "//*[@id='create_login']/div[1]//input[@type='email']")
    private WebElement registerEmailInput;

    @FindBy(xpath = "//*[@id='create_login']/div[1]//input[@type='password']")
    private WebElement registerPasswordInput;

    @FindBy(xpath = "//*[@id='create_login']/div[1]//button[@type='submit']")
    private WebElement registerSubmitButton;

    @FindBy(xpath = "//*[@id='create_login']/div[2]//h1")
    private String loginTitle;

    @FindBy(xpath = "//*[@id='create_login']/div[2]//input[@type='email']")
    private WebElement loginEmailInput;

    @FindBy(xpath = "//*[@id='create_login']/div[2]//input[@type='password']")
    private WebElement loginPasswordInput;

    @FindBy(xpath = "//*[@id='create_login']/div[2]//button[@type='submit']")
    private WebElement loginSubmitButton;

    @Value("${tests.values.login-page.email}")
    private String loginEmail;

    @Value("${tests.values.login-page.password}")
    private String loginPassword;

    public ANotepadLoginRegisterPage(WebDriver driver, ApplicationContext context) {
        super(driver, context);
    }

    public ANotepadLandingPage doLogin() {
        loginEmailInput.sendKeys(loginEmail);
        loginPasswordInput.sendKeys(loginPassword);
        loginSubmitButton.click();
        sleepUntil(() -> !isAtUrl(loginRegisterUrl));
        return new ANotepadLandingPage(driver, context);
    }

    public String getPageUrl() {
        return loginRegisterUrl;
    }

    public String getRegisterTitle() {
        return registerTitle;
    }

    public void setRegisterTitle(String registerTitle) {
        this.registerTitle = registerTitle;
    }

    public WebElement getRegisterEmailInput() {
        return registerEmailInput;
    }

    public void setRegisterEmailInput(WebElement registerEmailInput) {
        this.registerEmailInput = registerEmailInput;
    }

    public WebElement getRegisterPasswordInput() {
        return registerPasswordInput;
    }

    public void setRegisterPasswordInput(WebElement registerPasswordInput) {
        this.registerPasswordInput = registerPasswordInput;
    }

    public WebElement getRegisterSubmitButton() {
        return registerSubmitButton;
    }

    public void setRegisterSubmitButton(WebElement registerSubmitButton) {
        this.registerSubmitButton = registerSubmitButton;
    }

    public String getLoginTitle() {
        return loginTitle;
    }

    public void setLoginTitle(String loginTitle) {
        this.loginTitle = loginTitle;
    }

    public WebElement getLoginEmailInput() {
        return loginEmailInput;
    }

    public void setLoginEmailInput(WebElement loginEmailInput) {
        this.loginEmailInput = loginEmailInput;
    }

    public WebElement getLoginPasswordInput() {
        return loginPasswordInput;
    }

    public void setLoginPasswordInput(WebElement loginPasswordInput) {
        this.loginPasswordInput = loginPasswordInput;
    }

    public WebElement getLoginSubmitButton() {
        return loginSubmitButton;
    }

    public void setLoginSubmitButton(WebElement loginSubmitButton) {
        this.loginSubmitButton = loginSubmitButton;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
