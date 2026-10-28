package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BasePage {
    static WebDriver wd;

    public BasePage(WebDriver wd) {
        this.wd = wd;
        PageFactory.initElements(wd, this);
    }
    public void click(By locator){
        wd.findElement(locator).click();
    }

    public void type(WebElement locator, String text) {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        WebElement element = wait.until(visibilityOf(locator));

        ((JavascriptExecutor) wd)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        if (text != null) {
            element.clear();
            element.sendKeys(text);
        }
    }

    public void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
    }

    public void clearNew(WebElement element){
        String os = System.getProperty("os.name");
        System.out.println(os);


        element.sendKeys(" ");
//        element.sendKeys(Keys.BACK_SPACE);

        if(os.startsWith("Win")){
            element.sendKeys(Keys.CONTROL,"a");
        }else {
            element.sendKeys(Keys.COMMAND,"a");
        }
        element.sendKeys(Keys.DELETE);
    }


    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isElementPresent(By locator){
        List<WebElement> list = wd.findElements(locator);
        return list.size()>0;
    }

    public String getErrorText() {
        return wd.findElement(By.cssSelector("div.error")).getText();
    }
}
