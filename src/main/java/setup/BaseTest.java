package setup;

import static constants.AppConstants.*;
import static constants.CapabilitiesConstants.*;

import io.appium.java_client.AppiumDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.PageObject;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    IPageObject iPageObject;

    @Override
    public AppiumDriver getDriver() { return appiumDriver; }

    public IPageObject getIPageObject() {
        return iPageObject;
    }

    @Parameters({PLATFORM_NAME, APP_TYPE, DEVICE_NAME, BROWSER_NAME, APP})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, String deviceName,
                      @Optional("") String browserName, @Optional("") String app) throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, browserName, app);
        setPageObject(appType, appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String browserName, String app){
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(PLATFORM_NAME,platformName);
        capabilities.setCapability(DEVICE_NAME,deviceName);

        if(app.endsWith(APK_EXTENSION))
            capabilities.setCapability(APP, (new File(app)).getAbsolutePath());

        capabilities.setCapability(BROWSER_NAME, browserName);
        capabilities.setCapability(IGNORE_DRIVER_VERSION,TRUE);

        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty(APPIUM_PROPS)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        iPageObject = new PageObject(appType, appiumDriver);
    }
}
