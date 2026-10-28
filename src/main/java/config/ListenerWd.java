package config;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

public class ListenerWd implements WebDriverListener {
    Logger logger = LoggerFactory.getLogger(ListenerWd.class);

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElement(driver, locator);
        logger.info("Before find element-->" + locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        WebDriverListener.super.afterFindElement(driver, locator, result);
        logger.info("After find element-->" + locator);
        logger.info("Location of element-->" + result.getTagName());
    }

    @Override
    public void beforeClick(WebElement element) {
        WebDriverListener.super.beforeClick(element);
        logger.info("Before click element -->"+ element.getTagName()
                + "text-->" + element.getText());
    }

    @Override
    public void afterClick(WebElement element) {
        WebDriverListener.super.afterClick(element);
        logger.info("After click element -->"+ element.getTagName());
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        WebDriverListener.super.beforeSendKeys(element, keysToSend);
        logger.info("sendKeyds-->" + element.getText());
        logger.info("sendKeyds_TagName-->" + element.getTagName());
    }

    @Override
    public void beforeTo(WebDriver.Navigation navigation, URL url) {
        WebDriverListener.super.beforeTo(navigation, url);
        logger.info("Navigate to-->" + url);
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        WebDriverListener.super.onError(target, method, args, e);
        logger.info("Huston, we have a problem!");
        logger.info("Failed method:" +method.getName());
        logger.info("***************");
        logger.info("Arguments:"+ Arrays.toString(args));
        logger.info("***************");
        logger.info("Exception:"+e.getTargetException().getClass().getSimpleName());
        logger.info("***************");
        logger.info("Message"+e.getTargetException().getMessage());
        logger.info("***************");
        logger.info("***************");
        int i = new Random().nextInt(1000)+1000;
        String link = "src/test/screenshots/screen_"+i+".png";
        logger.info("Screen with error is--->" + link);

        WebDriver wd = (ChromeDriver)target;
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp,new File(link));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
