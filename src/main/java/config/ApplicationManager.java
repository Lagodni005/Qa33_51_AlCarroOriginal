package config;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ApplicationManager {
    @Getter
    WebDriver wd;
    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    public void init() {
        if(browser.equals(Browser.CHROME.browserName())){
            wd = new ChromeDriver();
            logger.info("All tests runs in Chrome Browser");

        } else if (browser.equals(Browser.EDGE.browserName())){
            wd = new EdgeDriver();
            logger.info("All tests runs in EDGE Browser");

        } else if (browser.equals(Browser.FIREFOX.browserName())) {
            wd = new FirefoxDriver();
            logger.info("All tests runs in FIREFOX Browser");

        } else {
            throw new RuntimeException("Unknown browser: " + browser);
        }

        logger.info("All tests runs in Chrome Browser");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverListener webDriverListener = new ListenerWd();
        wd = new EventFiringDecorator(webDriverListener).decorate(wd);
        wd.navigate().to("https://ilcarro.web.app/search");
        logger.info("The link --->" + wd.getCurrentUrl());

    }

    public void stop() {
        if (wd != null) {
            wd.quit();
            wd = null;
        }
    }

}
