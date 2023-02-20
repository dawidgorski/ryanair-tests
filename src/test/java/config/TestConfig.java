package config;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import utility.RyanairTestWatcher;

import static utility.Actions.waitForClickabilityAndClick;

public class TestConfig {

    private static WebDriver driver;
    public MainPage mainPage;

    @RegisterExtension
    static RyanairTestWatcher watcher = new RyanairTestWatcher(DriverSingleton.getInstance(), "src/test/java/screenshots");

    @BeforeAll
    public static void setupAll() {
        driver = DriverSingleton.getInstance();
        driver.get("https://www.ryanair.com/us/en");
        driver.manage().window().maximize();
        waitForClickabilityAndClick(By.cssSelector("[data-ref='cookie.accept-all']"));
    }

    @AfterAll
    public static void teardownAll() {
        driver.quit();
        DriverSingleton.quit();
    }

    @BeforeEach
    public void setupEach() {
        driver.get("https://www.ryanair.com/us/en");
        mainPage = new MainPage();
    }

}
