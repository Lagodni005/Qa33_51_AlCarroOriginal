package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SearchPage extends BasePage{

    @FindBy(xpath = "//a[text()='Log in']")
    WebElement LoginBtn;

    @FindBy(xpath = "//a[text()='Sign up']")
    WebElement SignUp;

    @FindBy(xpath = "//a[text()='Let the car work']")
    WebElement LetCarWorkBtn;

    @FindBy(xpath = "//a[text()='Terms of use']")
    WebElement TermesOfUseBtn;

    @FindBy(xpath = "//input[@id='city']")
    WebElement CiteText;

    @FindBy(xpath = "//input[@id='dates']")
    WebElement DatesText;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement YallaBtn;

    @FindBy(xpath = "//a[text()=' Logout ']")
    WebElement logoutBtn;

    @FindBy(xpath = "//button[text()='OK']")
    WebElement OkBtn;


    public SearchPage(WebDriver wd) {
        super(wd);
        PageFactory.initElements(wd, this);
    }

    public void ClickOkBtn(){
        OkBtn.click();
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[text()='Delete account']"));
    }

    public void clickOnLogoutBtn(){
        logoutBtn.click();
    }

    public void ClickOnYallaBtn(){
        YallaBtn.click();
    }

    public void ClickOnLoginBtn(){
        LoginBtn.click();
        new LoginPage(wd);
    }

    public void ClickOnSignUp(){
        SignUp.click();
        new RegistrationPage(wd);
    }

    public void ClickOnLetCarWork(){
        LetCarWorkBtn.click();
        new LetTheCarWorkPage(wd);
    }

    public void ClickOnTermesOfUse(){
        TermesOfUseBtn.click();
        new TermesOfUsePage(wd);
    }

    // city field uses Google autocomplete: the value is accepted only after a suggestion is chosen.
    // If Google Maps fails to load, an error popup appears instead of suggestions and covers the
    // dates field, so it is closed and the city stays invalid.
    public void fillCity(String city){
        type(CiteText, city);
        if (city == null || city.isEmpty()) {
            return;
        }
        By suggestion = By.cssSelector("div.pac-item");
        By mapsErrorOk = By.cssSelector("div.pac-container button.dismissButton");
        new WebDriverWait(wd, Duration.ofSeconds(10)).until(ExpectedConditions.or(
                ExpectedConditions.elementToBeClickable(suggestion),
                ExpectedConditions.elementToBeClickable(mapsErrorOk)));
        if (isElementPresent(suggestion)) {
            wd.findElement(suggestion).click();
        } else {
            wd.findElement(mapsErrorOk).click();
        }
    }

    // dates format: MM/dd/yyyy
    public void typePeriod(String from, String to){
        type(DatesText, from + " - " + to);
        DatesText.sendKeys(Keys.ESCAPE);
        CiteText.click();
    }

    public void leavePeriodEmpty(){
        DatesText.click();
        DatesText.sendKeys(Keys.ESCAPE);
        CiteText.click();
    }

    public String getDatesErrorText(){
        return wd.findElement(By.xpath(
                "//input[@id='dates']/ancestor::div[contains(@class,'input-container')]//div[contains(@class,'error')]"))
                .getText();
    }

    public boolean isYallaBtnEnabled(){
        return YallaBtn.isEnabled();
    }

    public boolean isResultsPageOpened(){
        return new WebDriverWait(wd, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("results"));
    }

    // dates format: MM/dd/yyyy; days in the calendar are <td aria-label="October 23, 2026">
    public void FindCarNowMethod(String from, String to){
        DatesText.click();
        selectCalendarDay(from);
        selectCalendarDay(to);
    }

    private void selectCalendarDay(String date){
        String label = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                .format(DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH));
        By day = By.xpath("//td[@aria-label='" + label + "']");
        By nextMonth = By.xpath("//button[@aria-label='Next month']");

        // the calendar shows one month at a time, so move forward until the day is visible
        for (int i = 0; i < 12 && !isElementPresent(day); i++) {
            wd.findElement(nextMonth).click();
        }
        new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(day))
                .click();
    }
}
