package com.brangelov.seleniumtests;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfig implements InitializingBean {

    @Value("${tests.gecko-driver}")
    private String geckoDriverLocation;

    @Value("${tests.chrome-driver}")
    private String chromeDriverLocation;

    @Value("${tests.supported-browser}")
    private String[] supportedBrowsers;

    @Value("${tests.default-timeout:5}")
    private int defaultTimeoutSeconds;

    public String getGeckoDriverLocation() {
        return geckoDriverLocation;
    }

    public void setGeckoDriverLocation(String geckoDriverLocation) {
        this.geckoDriverLocation = geckoDriverLocation;
    }

    public String[] getSupportedBrowsers() {
        return supportedBrowsers;
    }

    public void setSupportedBrowsers(String[] supportedBrowsers) {
        this.supportedBrowsers = supportedBrowsers;
    }

    public int getDefaultTimeoutSeconds() {
        return defaultTimeoutSeconds;
    }

    public void setDefaultTimeoutSeconds(int defaultTimeoutSeconds) {
        this.defaultTimeoutSeconds = defaultTimeoutSeconds;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty("webdriver.gecko.driver", geckoDriverLocation);
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
    }
}
