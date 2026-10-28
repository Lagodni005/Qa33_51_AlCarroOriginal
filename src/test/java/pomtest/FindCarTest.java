package pomtest;

import dataProvider.DataProviderFindCar;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;

public class FindCarTest extends TestBase {

    // ---------- Positive ----------

    @Test(dataProvider = "FindCarSuccess", groups = "regress", dataProviderClass = DataProviderFindCar.class)
    public void FindCarSuccess(String city, String from, String to){
        logger.info("Test data ---> city: " + city + ", from: " + from + ", to: " + to);
        SearchPage searchPage = new SearchPage(app.getWd());
        searchPage.fillCity(city);
        //searchPage.ClickOkBtn();
        searchPage.FindCarNowMethod(from, to);
        searchPage.ClickOnYallaBtn();
        logger.info("Assert check: results page is opened");
        Assert.assertTrue(searchPage.isResultsPageOpened());
    }

    // ---------- Negative ----------

    @Test(dataProvider = "EmptyCity", groups = "regress", dataProviderClass = DataProviderFindCar.class)
    public void FindCarEmptyCityTest(String city, String from, String to){
        logger.info("Test data ---> city: " + city + ", from: " + from + ", to: " + to);
        SearchPage searchPage = new SearchPage(app.getWd());
        searchPage.fillCity(city);
        searchPage.typePeriod(from, to);
        logger.info("Assert check: Y'alla button is disabled");
        Assert.assertFalse(searchPage.isYallaBtnEnabled());
    }

    @Test(dataProvider = "WrongPeriod", groups = "regress", dataProviderClass = DataProviderFindCar.class)
    public void FindCarWrongPeriodTest(String city, String from, String to, String expectedError){
        logger.info("Test data ---> city: " + city + ", from: " + from + ", to: " + to);
        SearchPage searchPage = new SearchPage(app.getWd());
        searchPage.fillCity(city);
        searchPage.typePeriod(from, to);
        logger.info("Assert check: dates error '" + expectedError + "' is shown and Y'alla button is disabled");
        Assert.assertTrue(searchPage.getDatesErrorText().contains(expectedError));
        Assert.assertFalse(searchPage.isYallaBtnEnabled());
    }

    @Test(groups = {"regress"})
    public void FindCarEmptyPeriodTest() {
        logger.info("Test data ---> city: Haifa, dates: empty");
        SearchPage searchPage = new SearchPage(app.getWd());
        searchPage.fillCity("Haifa");
        searchPage.leavePeriodEmpty();
        logger.info("Assert check: dates error 'Dates are required' is shown and Y'alla button is disabled");
        Assert.assertTrue(searchPage.getDatesErrorText().contains("Dates are required"));
        Assert.assertFalse(searchPage.isYallaBtnEnabled());
    }
}
