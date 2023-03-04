package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
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
            try {
                browserName = PropertiesLoader.loadProperty("browser");
            } catch (IOException e) {
                browserName = "chrome";
            }
            boolean headless;
            try {
                headless = Boolean.parseBoolean(PropertiesLoader.loadProperty("headless"));
            } catch (IOException e) {
                e.printStackTrace();
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
                options.addArguments("-headless");
                return WebDriverManager.firefoxdriver().capabilities(options).create();
            }
            return WebDriverManager.firefoxdriver().create();
        } else if (browserName.equals("edge")) {
            if (headless) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=new");
                return WebDriverManager.edgedriver().capabilities(options).create();
            }
            return WebDriverManager.edgedriver().create();
        } else {
            if (headless) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                return WebDriverManager.chromedriver().capabilities(options).create();
            }

        }
        return WebDriverManager.chromedriver().create();
    }
}
