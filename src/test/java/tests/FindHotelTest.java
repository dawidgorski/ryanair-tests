package tests;

import config.TestConfig;
import org.junit.jupiter.api.Test;
import pages.HotelsTab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utility.Actions.getTodayDate;
import static utility.Actions.getTomorrowDate;

public class FindHotelTest extends TestConfig {

    @Test
    public void showHotelsList() {
        HotelsTab hotelsTab = mainPage.openHotelsTab();
        hotelsTab.enterLocationOrProperty("Berlin")
                .enterSimpleCheckInAndCheckout(getTodayDate(), getTomorrowDate())
                .clickSearchButton();
        assertTrue(hotelsTab.checkVisibilityOfRoomslist());
    }

    @Test
    public void searchHotelsWithoutDates() {
        HotelsTab hotelsTab = mainPage.openHotelsTab();
        hotelsTab.enterLocationOrProperty("Warsaw")
                .clickSearchButton();
        assertEquals("Please select travel dates", hotelsTab.getCheckInError());
    }
}
