package pages;

import model.Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
    @FindBy(xpath = "//input[@id='email']")
    WebElement emailText;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordText;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement YallaBtn;

    public LoginPage(WebDriver wd) {
        super(wd);
        PageFactory.initElements(wd, this);
    }

    public void fillLoginForm(Login login){
        type(emailText, login.getEmail());
        type(passwordText, login.getPassword());
    }

    public void ClickOnYallaBtnLog(){
        YallaBtn.click();
        new SearchPage(wd);
    }
}
