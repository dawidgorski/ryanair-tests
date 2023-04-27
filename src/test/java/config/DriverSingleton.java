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
            FirefoxOptions options = new FirefoxOptions();
            if (headless) {
                options.addArguments("-headless");
            }
            return new FirefoxDriver(options);
        } else if (browserName.equals("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (headless) {
                options.addArguments("--headless=new");
            }
            return new EdgeDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless=new");
            }
            return new ChromeDriver(options);
        }
    }
}
