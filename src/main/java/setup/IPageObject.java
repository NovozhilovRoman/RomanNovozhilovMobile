package setup;

import java.util.List;
import org.openqa.selenium.WebElement;

public interface IPageObject {

    WebElement getElement(String element)
        throws NoSuchFieldException, IllegalAccessException, InstantiationException;

    List<WebElement> getElements(String elements)
        throws NoSuchFieldException, IllegalAccessException, InstantiationException;
}
