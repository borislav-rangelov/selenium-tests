package com.brangelov.seleniumtests.common;

import com.brangelov.seleniumtests.SeleniumConfig;
import com.brangelov.seleniumtests.SeleniumTestsApplication;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SeleniumTestsApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
@Import({SeleniumConfig.class})
public abstract class SeleniumTest {

    @Autowired
    protected ApplicationContext context;

    @Autowired
    protected SeleniumConfig seleniumConfig;

    protected void doForEachBrowser(BiConsumer<String, WebDriver> consumer) {
        for (String browser : seleniumConfig.getSupportedBrowsers()) {
            WebDriver driver = getDriver(browser);
            try {
                consumer.accept(browser, driver);
            } finally {
                driver.quit();
            }
        }
    }

    private WebDriver getDriver(String name) {
        WebDriver webDriver;
        switch (name) {
            case "firefox":
                webDriver = new FirefoxDriver(); break;
            case "chrome":
                webDriver = new ChromeDriver(); break;
            default:
                throw new RuntimeException("Unknown driver: " + name);
        }
        setDriverTimeout(webDriver);
        return webDriver;
    }

    private void setDriverTimeout(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(seleniumConfig.getDefaultTimeoutSeconds(), TimeUnit.SECONDS);
    }
}
