package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NativePageObject {

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    WebElement loginEmailField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    WebElement loginPassField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    WebElement signInBtn;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    WebElement registerBtn;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    WebElement registrationEmailField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    WebElement registrationUserNameField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    WebElement registrationPasswordField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    WebElement registrationPasswordConfirmField;
    @AndroidFindBy(className = "android.widget.CheckedTextView")
    WebElement agreementCheckbox;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    WebElement registerNewAccBtn;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_cancel_button")
    WebElement cancelBtn;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='BudgetActivity']")
    WebElement pageTitle;

    public NativePageObject(AppiumDriver<? extends WebElement> appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }
}
