package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    public static String loadProperty(String propertyName) throws IOException {
        String property = System.getenv(propertyName);
        if (property != null) {
            return property;
        }
        InputStream inputStream = new FileInputStream("src/test/resources/properties/config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties.getProperty(propertyName);
    }
}
