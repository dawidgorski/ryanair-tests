package tests;

import config.TestConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utility.Actions.getTodayDate;
import static utility.Actions.getTomorrowDate;

public class FindHotelTest extends TestConfig {

    @Test
    public void showHotelsList() {
        boolean visibilityOfRoomsList = mainPage
                .openHotelsTab()
                .enterLocationOrProperty("Berlin")
                .enterSimpleCheckInAndCheckout(getTodayDate(), getTomorrowDate())
                .clickSearchButton()
                .checkVisibilityOfRoomslist();
        assertTrue(visibilityOfRoomsList);
    }

    @Test
    public void searchHotelsWithoutDates() {
        String checkInError = mainPage
                .openHotelsTab()
                .enterLocationOrProperty("Warsaw")
                .clickSearchButton()
                .getCheckInError();
        assertEquals("Please select travel dates", checkInError);
    }
}
