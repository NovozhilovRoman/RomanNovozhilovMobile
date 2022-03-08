package setup;

import com.google.gson.Gson;
import entities.NativeAndroidTestData;
import entities.WebTestData;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class DataProviders {

    @DataProvider(name = "dataProviderForNativeTest")
    public static Object[][] dataProviderForNativeTest() throws FileNotFoundException {
        Reader reader = new FileReader("src/test/resources/DataForNativeAndroidTests.json");
        NativeAndroidTestData data = new Gson().fromJson(reader, NativeAndroidTestData.class);

        return new Object[][]{
                {data.getEmail(), data.getUsername(), data.getPassword(), data.getPageTitle()},
        };
    }


    @DataProvider(name = "dataProviderForWebTest")
    public static Object[][] dataProviderForWebTest() throws FileNotFoundException {
        Reader reader = new FileReader("src/test/resources/DataForWebTests.json");
        WebTestData data = new Gson().fromJson(reader, WebTestData.class);

        return new Object[][]{
                {data.getURL(), data.getPageTitle(), data.getSearchPhrase()},
        };
    }
}
