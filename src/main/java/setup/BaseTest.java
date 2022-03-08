package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.PageObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest implements IDriver {

    private static AppiumDriver<? extends WebElement> appiumDriver;
    static IPageObject page;

    @Override
    public AppiumDriver<? extends WebElement> getDriver() {
        return appiumDriver;
    }

    public IPageObject getPage() {
        return page;
    }

    @Parameters({"platformName", "appType", "deviceName", "browserName", "app"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, String deviceName, @Optional("") String browserName, @Optional("") String app) throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, browserName, app);
        setPageObject(appType, appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String browserName, String app) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);

        if (app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        //mandatory web capabilities
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        try {
            appiumDriver = new AppiumDriver<>(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    protected void setPageObject(String appType, AppiumDriver<? extends WebElement> appiumDriver) throws Exception {
        page = new PageObject(appType, appiumDriver);
    }

}
