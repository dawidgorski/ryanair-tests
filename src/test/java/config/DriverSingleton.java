package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class DriverSingleton {
    private DriverSingleton() {
    }

    private static WebDriver instance;

    public static WebDriver getInstance() {
        if (instance == null) {
            WebDriverManager.chromedriver().setup();
            instance = new ChromeDriver();
        }
        return instance;
    }
    public static void quit(){
        if(null != instance){
            instance.quit();
        }
        instance = null;
    }
}
