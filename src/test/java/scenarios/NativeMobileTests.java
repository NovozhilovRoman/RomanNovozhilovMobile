package scenarios;

import org.testng.annotations.Test;
import setup.BaseTest;
import setup.DataProviders;

public class NativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "Registration and sign in test",
            dataProvider = "dataProviderForNativeTest", dataProviderClass = DataProviders.class)
    public void simpleNativeTest(String email, String username, String password, String pageTitle) throws
            IllegalAccessException, NoSuchFieldException, InstantiationException {

        getPage().getWebElement("registerBtn").click();
        getPage().getWebElement("registrationEmailField").sendKeys(email);
        getPage().getWebElement("registrationUserNameField").sendKeys(username);
        getPage().getWebElement("registrationPasswordField").sendKeys(password);
        getPage().getWebElement("registrationPasswordConfirmField").sendKeys(password);
        getPage().getWebElement("agreementCheckbox").click();
        getPage().getWebElement("registerNewAccBtn").click();

        getPage().getWebElement("loginEmailField").sendKeys(email);
        getPage().getWebElement("loginPassField").sendKeys(password);
        getPage().getWebElement("signInBtn").click();

        assert (getPage().getWebElement("pageTitle").getText().equals(pageTitle));
    }
}
