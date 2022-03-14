package scenarios;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestPropertiesLoader.getProperty;

import org.testng.annotations.Test;
import setup.BaseTest;

public class NativeMobileTests extends BaseTest {

    @Test(groups = {"native"})
    public void testSuccessfulSingIn()
        throws IllegalAccessException, NoSuchFieldException, InstantiationException {

        /*Registration activity*/

        //Open registration page by clicking on register button
        getIPageObject().getElement("registerButton").click();

        //Input register email
        getIPageObject().getElement("registerEmailField")
                        .sendKeys(getProperty("email"));

        //Input register username
        getIPageObject().getElement("registerUsernameField")
                        .sendKeys(getProperty("username"));

        //Input register password
        getIPageObject().getElement("registerPasswordField")
                        .sendKeys(getProperty("password"));

        //Confirm register password
        getIPageObject().getElement("registerConfirmPasswordField")
                        .sendKeys(getProperty("password"));

        // Hide keyboard to continue interactions
        getDriver().hideKeyboard();

        //Complete registration by clicking on register new account button
        getIPageObject().getElement("registerNewAccountButton").click();

        /*SignIn Activity*/

        //Input registered email
        getIPageObject().getElement("loginField")
                        .sendKeys(getProperty("email"));

        //Input registered password
        getIPageObject().getElement("passwordField")
                        .sendKeys(getProperty("password"));

        //Perform login by clicking on sign in button
        getIPageObject().getElement("signInButton").click();


        /*Budget Activity*/

        //Make sure I'm on the BudgetActivity page
        String activityName = getIPageObject().getElement("activity").getText();
        assertThat(activityName).as("Incorrect screen name").startsWith("Budget");

        System.out.println("Android native test done");
    }
}
