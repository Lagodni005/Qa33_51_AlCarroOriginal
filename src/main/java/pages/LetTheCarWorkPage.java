package pages;

import model.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LetTheCarWorkPage extends BasePage{

    @FindBy(xpath = "//input[@id='pickUpPlace']")
    WebElement locationText;

    @FindBy(xpath = "//input[@id='make']")
    WebElement manufacture;

    @FindBy(xpath = "//input[@id='model']")
    WebElement modelText;

    @FindBy(xpath = "//input[@id='year']")
    WebElement yearText;

    @FindBy(xpath = "//select[@id='fuel']")
    WebElement fuelText;

    @FindBy(xpath = "//input[@id='seats']")
    WebElement seatsText;

    @FindBy(xpath = "//input[@id='class']")
    WebElement carClassText;

    @FindBy(xpath = "//input[@id='serialNumber']")
    WebElement idCarText;

    @FindBy(xpath = "//input[@id='price']")
    WebElement priceText;

    @FindBy(xpath = "//input[@id='about']")
    WebElement aboutText;

    @FindBy(xpath = "//button[text()='Submit']")
    WebElement submitBtn;

    public LetTheCarWorkPage(WebDriver wd) {
        super(wd);
        PageFactory.initElements(wd, this);
    }

    public void fillLetTheCarWork(Car car){
        type(locationText, car.getLocation());
        type(manufacture, car.getManufacture());
        type(modelText, car.getModel());
        type(yearText, car.getYear());
        select((By) fuelText, car.getFuel());
        type(seatsText, String.valueOf(car.getSeats()));
        type(carClassText, car.getCarClass());
        type(idCarText, car.getCarRegNumber());
        type(priceText, String.valueOf(car.getPrice()));
        type(aboutText, car.getAbout());
    }

    public void ClickOnSubmitBtn(){
        submitBtn.click();
    }

    public void attachPhoto(String link) {
        wd.findElement(By.cssSelector("#photos")).sendKeys(link);
    }
}
