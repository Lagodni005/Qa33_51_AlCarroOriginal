package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TermesOfUsePage extends BasePage{
    public TermesOfUsePage(WebDriver wd) {
        super(wd);
        PageFactory.initElements(wd, this);
    }
}
