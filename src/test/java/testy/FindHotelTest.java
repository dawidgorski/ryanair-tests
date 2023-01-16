package testy;

import config.TestConfig;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utility.Actions.*;

public class FindHotelTest extends TestConfig {

    @Test
    public void showHotelsList(){
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='hotels']"));
        waitForVisibilityAndSendKeys(By.cssSelector("#input-button__locations-or-properties"), "Warsaw");
        waitForClickabilityAndClick(By.cssSelector("div.tooltip-inner [classlist='icon-14']"));
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='Search']"));
        waitForClickabilityAndClick(By.cssSelector("[uniqueid='check-in'"));
        waitForClickabilityAndClick(By.cssSelector("[role='tooltip'] [data-id='" + getTodayDate() + "']"));
        waitForClickabilityAndClick(By.cssSelector("[role='tooltip'] [data-id='" + getTomorrowDate() + "']"));
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='Search']"));
        assertTrue(waitForVisibility(By.cssSelector("rooms-list")));

    }

    @Test
    public void searchHotelsWithoutDates(){
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='hotels']"));
        waitForVisibilityAndSendKeys(By.cssSelector("#input-button__locations-or-properties"), "Warsaw");
        waitForClickabilityAndClick(By.cssSelector("div.tooltip-inner [classlist='icon-14']"));
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='Search']"));
        assertEquals("Please select travel dates",waitForVisibilityAndGetText(By.cssSelector("[uniqueid='check-in'] [data-ref='input-button__error']")));
    }
}
