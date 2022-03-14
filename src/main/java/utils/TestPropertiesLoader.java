package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class TestPropertiesLoader {

    private final static String PROPS_PATH = "src/test/resources/test.properties";

    public static String getProperty(String property) {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(PROPS_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(property);
    }
}
