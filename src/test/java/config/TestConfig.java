package config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.MainPage;

import static utility.Actions.waitForClickabilityAndClick;

public class TestConfig {

    private static WebDriver driver;
    public MainPage mainPage;

    @BeforeMethod
    public void setupEach() {
        driver = DriverSingleton.getInstance();
        driver.get("https://www.ryanair.com/us/en");
        driver.manage().window().maximize();
        waitForClickabilityAndClick(By.cssSelector("[data-ref='cookie.accept-all']"));
        mainPage = new MainPage();
    }

    @AfterMethod
    public void teardownEach() {
        driver.quit();
        DriverSingleton.quit();
    }

}
