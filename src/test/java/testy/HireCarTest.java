package testy;

import config.TestConfig;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static utility.Actions.*;

public class HireCarTest extends TestConfig {

    @Test
    public void showCarList(){
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='Car Hire']"));
        waitForVisibilityAndSendKeys(By.cssSelector("#input-button__pick-up-location"), "Warsaw Modlin");
        waitForClickabilityAndClick(By.cssSelector("div.tooltip-inner [classlist='icon-18']"));
        waitForClickabilityAndClick(By.cssSelector("button[aria-label=\"Let's go\"]"));
        assertTrue(waitForVisibility(By.cssSelector("div.car-hire-container__header")));
    }
}
