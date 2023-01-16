package config;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utility.Actions.waitForClickabilityAndClick;

public class TestConfig {
    public static WebDriver driver;

    @BeforeAll
    public static void setupAll(){
        driver = DriverSingleton.getInstance();
        driver.get("https://www.ryanair.com/us/en");
        driver.manage().window().maximize();
        waitForClickabilityAndClick(By.cssSelector("[data-ref='cookie.accept-all']"));
    }

    @BeforeEach
    public void setupEach(){
        driver.get("https://www.ryanair.com/us/en");

    }

    @AfterAll
    public static void teardownAll(){
    driver.quit();
    DriverSingleton.quit();
    }

}
