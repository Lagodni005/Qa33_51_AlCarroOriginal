package pages;

import model.Registration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BasePage{

    @FindBy(xpath = "//input[@id='name']")
    WebElement nameText;

    @FindBy(xpath = "//input[@id='lastName']")
    WebElement lastNameText;

    @FindBy(xpath = "//input[@id='email']")
    WebElement emailText;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordText;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement YallaBtn;

    public RegistrationPage(WebDriver wd) {
        super(wd);
        PageFactory.initElements(wd, this);
    }

    public void fillRegistrationForm(Registration registration){
        type(nameText, registration.getName());
        type(lastNameText, registration.getLastName());
        type(emailText, registration.getEmail());
        type(passwordText, registration.getPassword());
    }

    @FindBy(xpath = "//input[@id='terms-of-use']")
    WebElement termsCheckbox;

    public void clickOnYallaBtnReg(){
        YallaBtn.click();
    }

    // the checkbox input is covered by a custom label, so a regular click() is intercepted
    public void checkTermsOfUse(){
        if (!termsCheckbox.isSelected()) {
            ((JavascriptExecutor) wd).executeScript("arguments[0].click();", termsCheckbox);
        }
    }

    public boolean isYallaBtnEnabled(){
        return YallaBtn.isEnabled();
    }
}
