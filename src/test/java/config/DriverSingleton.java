package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utility.PropertiesLoader;

import java.io.IOException;

public final class DriverSingleton {

    private static WebDriver instance;

    private DriverSingleton() {
    }

    public static WebDriver getInstance() {
        if (instance == null) {
            String browserName;
            boolean headless;
            try {
                browserName = PropertiesLoader.loadProperty("browser.name");
                headless = Boolean.parseBoolean(PropertiesLoader.loadProperty("browser.headless"));

            } catch (IOException e) {
                browserName = "chrome";
                headless = false;
            }
            instance = browserFactory(browserName, headless);
        }
        return instance;
    }

    public static void quit() {
        if (null != instance) {
            instance.quit();
        }
        instance = null;
    }

    private static WebDriver browserFactory(String browserName, boolean headless) {
        if (browserName.equals("firefox")) {
            if (headless) {
                FirefoxOptions options = new FirefoxOptions();
                options.setHeadless(true);
                return new FirefoxDriver(options);
            }
            return new FirefoxDriver();
        } else if (browserName.equals("edge")) {
            if (headless) {
                EdgeOptions options = new EdgeOptions();
                options.setHeadless(true);
                return new EdgeDriver(options);
            }
            return new EdgeDriver();
        } else {
            if (headless) {
                ChromeOptions options = new ChromeOptions();
                options.setHeadless(true);
                return new ChromeDriver(options);
            }

        }
        return new ChromeDriver();
    }
}
