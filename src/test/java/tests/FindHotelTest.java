package tests;

import config.TestConfig;
import org.testng.annotations.Test;
import pages.HotelsTab;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
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
        assertEquals(hotelsTab.getCheckInError(), "Please select travel dates");
    }
}
