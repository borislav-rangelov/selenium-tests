package com.brangelov.seleniumtests.pages;

import com.brangelov.seleniumtests.SeleniumConfig;
import com.brangelov.seleniumtests.Threads;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import java.time.Instant;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class WebPage {

    protected final WebDriver driver;

    protected final ApplicationContext context;

    @Autowired
    protected SeleniumConfig config;

    public WebPage(WebDriver driver, ApplicationContext context) {
        this.driver = driver;
        this.context = context;
        PageFactory.initElements(driver, this);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    public boolean isAtUrl(String url) {
        return Objects.equals(driver.getCurrentUrl(), url);
    }

    public void sleep() {
        sleep(config.getDefaultTimeoutSeconds());
    }

    public void sleepUntil(Supplier<Boolean> check) {
        long timeout = Instant.now().getEpochSecond() + config.getDefaultTimeoutSeconds();
        while (!check.get()) {
            if (Instant.now().getEpochSecond() < timeout)
                Threads.sleep(1000);
            else
                Assert.fail("Sleep until timeout");
        }
    }

    public void sleep(int seconds) {
        Threads.sleep(seconds * 1000);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
