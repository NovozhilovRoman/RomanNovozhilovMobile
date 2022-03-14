package setup;

import static constants.AppConstants.*;
import static constants.CapabilitiesConstants.*;
import static utils.TestPropertiesLoader.getProperty;

import io.appium.java_client.AppiumDriver;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.PageObject;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    IPageObject iPageObject;

    private static final String PROJECT = getProperty("project");
    private static final String TOKEN = getProperty("token");
    private static final String HUB = getProperty("hub");

    @Override
    public AppiumDriver getDriver() { return appiumDriver; }

    public IPageObject getIPageObject() {
        return iPageObject;
    }

    @Parameters({PLATFORM_NAME, APP_TYPE, DEVICE_NAME, BROWSER_NAME, APP,
        UDID, APP_PACKAGE, APP_ACTIVITY, BUNDLE_ID})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName,
                      @Optional("") String appType,
                      @Optional("") String deviceName,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String udid,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId) throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, browserName, app, udid, appPackage, appActivity, bundleId);
        setPageObject(appType, appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String browserName, String app,
                                 String udid, String appPackage, String appActivity, String bundleId)
        throws UnsupportedEncodingException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(PLATFORM_NAME,platformName);
        capabilities.setCapability(DEVICE_NAME,deviceName);
        capabilities.setCapability(UDID, udid);

        if(app.endsWith(APK_EXTENSION))
            capabilities.setCapability(APP, (new File(app)).getAbsolutePath());

        capabilities.setCapability(BROWSER_NAME, browserName);
        capabilities.setCapability(IGNORE_DRIVER_VERSION,TRUE);

        capabilities.setCapability(APP_PACKAGE, appPackage);
        capabilities.setCapability(APP_ACTIVITY, appActivity);

        capabilities.setCapability(BUNDLE_ID, bundleId);

        String token = URLEncoder.encode(TOKEN, StandardCharsets.UTF_8.name());
        try {
            appiumDriver = new AppiumDriver(new URL(
                String.format("https://%s:%s@%s/wd/hub", PROJECT, token, HUB)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        iPageObject = new PageObject(appType, appiumDriver);
    }
}
