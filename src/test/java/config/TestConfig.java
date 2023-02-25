package config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.MainPage;

import static utility.Actions.waitForClickabilityAndClick;

public class TestConfig {

    private static WebDriver driver;
    public MainPage mainPage;

    @BeforeClass
    public void setupAll() {
        driver = DriverSingleton.getInstance();
        driver.get("https://www.ryanair.com/us/en");
        driver.manage().window().maximize();
        waitForClickabilityAndClick(By.cssSelector("[data-ref='cookie.accept-all']"));
    }

    @AfterClass
    public void teardownAll() {
        driver.quit();
        DriverSingleton.quit();
    }

    @BeforeMethod
    public void setupEach() {
        driver.get("https://www.ryanair.com/us/en");
        mainPage = new MainPage();
    }

}
