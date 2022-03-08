package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WebPageObject {

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchField;
    @FindBy(xpath = "//div[@id='rso']/div")
    List<WebElement> searchResults;

    public WebPageObject(AppiumDriver<? extends WebElement> appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }

}
