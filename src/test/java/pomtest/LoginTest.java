package pomtest;

import dataProvider.DataProviderLogin;
import model.Login;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchPage;

public class LoginTest extends TestBase {
    @BeforeMethod
    public void preCondition(){
        SearchPage searchPage = new SearchPage(app.getWd());
        if (searchPage.isLogged()){
            searchPage.clickOnLogoutBtn();
        }
    }

    @Test(dataProvider = "Login", groups = "smoke", dataProviderClass = DataProviderLogin.class)
    public void LoginSuccess(Login login){
        logger.info("Test data ---> " + login);
        SearchPage searchPage = new SearchPage(app.getWd());
        searchPage.ClickOnLoginBtn();
        LoginPage loginPage = new LoginPage(app.getWd());
        loginPage.fillLoginForm(login);
        loginPage.ClickOnYallaBtnLog();
        logger.info("Assert check: message 'Logged in' is present");
        Assert.assertTrue(searchPage.isElementPresent(By.xpath("//h1[text()='Logged in']")));
    }
    
    @Test(dataProvider = "WrongEmail", groups = "smoke", dataProviderClass = DataProviderLogin.class)
    public void WrongLoginEmailTest(Login login){
        logger.info("Test data ---> " + login);
        SearchPage searchPage = new SearchPage(app.getWd());
        searchPage.ClickOnLoginBtn();
        LoginPage loginPage = new LoginPage(app.getWd());
        loginPage.fillLoginForm(login);
        loginPage.ClickOnYallaBtnLog();
        loginPage.pause(2000);
        logger.info("Assert check: error 'It'snot look like email' is present");
        Assert.assertTrue(LoginPage.isElementPresent(By.xpath("//div[text()=\"It'snot look like email\"]")));
    }

    @Test(dataProvider = "WrongPassword" , groups = "regress", dataProviderClass = DataProviderLogin.class)
    public void WrongLoginPasswordTest(Login login){
        logger.info("Test data ---> " + login);
        SearchPage searchPage = new SearchPage(app.getWd());
        searchPage.ClickOnLoginBtn();
        LoginPage loginPage = new LoginPage(app.getWd());
        loginPage.fillLoginForm(login);
        loginPage.ClickOnYallaBtnLog();
        logger.info("Assert check: message 'Login failed' is present");
        Assert.assertTrue(searchPage.isElementPresent(By.xpath("//h1[text()='Login failed']")));
    }
}

