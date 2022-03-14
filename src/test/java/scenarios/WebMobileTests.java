package scenarios;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestPropertiesLoader.getProperty;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObjects.WebPageObject;
import setup.BaseTest;

public class WebMobileTests extends BaseTest {

    @Test(groups = {"web"})
    public void testSuccessfulGoogleSearch() throws InterruptedException {

        //Open Google search page
        getDriver().get(getProperty("google_url"));

        new WebDriverWait(getDriver(), 100)
            .until(wd -> ((JavascriptExecutor) wd)
                .executeScript("return document.readyState")
                .equals("complete"));

        WebPageObject webPageObject = new WebPageObject(getDriver());

        //Make a search using keyword 'EPAM'
        WebElement searchField = webPageObject.getSearchField();
        searchField.click();
        searchField.sendKeys(getProperty("query") + "\n");

        //Make sure there are some relevant results
        List<WebElement> searchResults = webPageObject.getSearchResults();
        for(WebElement result : searchResults){
           assertThat(result.getText())
               .as("No relevant results found")
               .contains(getProperty("query"));
        }

        System.out.println("Android web test done");
    }
}
