package pomtest;

import config.ApplicationManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    static ApplicationManager app = new ApplicationManager
            (System.getProperty("browser", Browser.CHROME.browserName()));

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        app.init();
        logger.info("****************************");
    }

    @BeforeMethod(alwaysRun = true)
    public void startLogger(Method m){
        Allure.step("Start test: " + m.getName());
        logger.info("Name of method (test) --->" + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    // used by TestNGListener (via reflection) to take a screenshot on failure
    public ApplicationManager getApp() {
        return app;
    }
}
