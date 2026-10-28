package pomtest;

import dataProvider.DataProviderRegistration;
import model.Registration;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import pages.SearchPage;

public class RegistrationTest extends TestBase {
    @BeforeMethod
    public void preCondition(){
        SearchPage searchPage = new SearchPage(app.getWd());
        if (searchPage.isLogged()){
            searchPage.clickOnLogoutBtn();
        }
    }

    private RegistrationPage openAndFillRegistrationForm(Registration registration){
        SearchPage searchPage = new SearchPage(app.getWd());
        searchPage.ClickOnSignUp();
        RegistrationPage registrationPage = new RegistrationPage(app.getWd());
        registrationPage.fillRegistrationForm(registration);
        return registrationPage;
    }

    // ---------- Positive ----------

    @Test(dataProvider = "RegistrationSuccess", groups = "smoke", dataProviderClass = DataProviderRegistration.class)
    public void RegistrationSuccess(Registration registration){
        logger.info("Test data ---> " + registration);
        RegistrationPage registrationPage = openAndFillRegistrationForm(registration);
        registrationPage.checkTermsOfUse();
        registrationPage.clickOnYallaBtnReg();
        registrationPage.pause(2000);
        logger.info("Assert check: message 'Registered' is present");
        Assert.assertTrue(RegistrationPage.isElementPresent(By.xpath("//h1[text()='Registered']")));
    }

    // ---------- Negative ----------

    @Test(dataProvider = "EmptyName", groups = "regress", dataProviderClass = DataProviderRegistration.class)
    public void RegistrationEmptyNameTest(Registration registration){
        logger.info("Test data ---> " + registration);
        RegistrationPage registrationPage = openAndFillRegistrationForm(registration);
        registrationPage.checkTermsOfUse();
        logger.info("Assert check: Y'alla button is disabled");
        Assert.assertFalse(registrationPage.isYallaBtnEnabled());
    }

    @Test(dataProvider = "EmptyLastName", groups = "regress", dataProviderClass = DataProviderRegistration.class)
    public void RegistrationEmptyLastNameTest(Registration registration){
        logger.info("Test data ---> " + registration);
        RegistrationPage registrationPage = openAndFillRegistrationForm(registration);
        registrationPage.checkTermsOfUse();
        logger.info("Assert check: Y'alla button is disabled");
        Assert.assertFalse(registrationPage.isYallaBtnEnabled());
    }

    @Test(dataProvider = "WrongEmail", groups = "smoke", dataProviderClass = DataProviderRegistration.class)
    public void RegistrationWrongEmailTest(Registration registration){
        logger.info("Test data ---> " + registration);
        RegistrationPage registrationPage = openAndFillRegistrationForm(registration);
        registrationPage.checkTermsOfUse();
        logger.info("Assert check: Y'alla button is disabled");
        Assert.assertFalse(registrationPage.isYallaBtnEnabled());
    }

    @Test(dataProvider = "WrongPassword", groups = "smoke", dataProviderClass = DataProviderRegistration.class)
    public void RegistrationWrongPasswordTest(Registration registration){
        logger.info("Test data ---> " + registration);
        RegistrationPage registrationPage = openAndFillRegistrationForm(registration);
        registrationPage.checkTermsOfUse();
        logger.info("Assert check: Y'alla button is disabled");
        Assert.assertFalse(registrationPage.isYallaBtnEnabled());
    }

    @Test(dataProvider = "RegistrationSuccess", groups = "regress", dataProviderClass = DataProviderRegistration.class)
    public void RegistrationWithoutTermsOfUseTest(Registration registration){
        logger.info("Test data ---> " + registration);
        RegistrationPage registrationPage = openAndFillRegistrationForm(registration);
        logger.info("Assert check: Y'alla button is disabled without terms of use");
        Assert.assertFalse(registrationPage.isYallaBtnEnabled());
    }
}
