package tests;

import config.TestConfig;
import org.junit.jupiter.api.Test;
import pages.FlightsTab;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static utility.Actions.getDayAfterTomorrowDate;
import static utility.Actions.getTomorrowDate;

public class SearchFlightsTest extends TestConfig {
    //no screenshot ?????
    @Test
    public void searchFlightExactDatesTodayTomorrowOneAdultCorrectData() {
        FlightsTab flightsTab = mainPage.openFlightsTab();
        flightsTab.openFlightsTab()
                .setFlightDeparture("Warsaw Modlin")
                .setFlightDestination("London Stansted")
                .setExactDates(getTomorrowDate(), getDayAfterTomorrowDate())
                .clickDoneAndSearchButton();
        assertTrue(flightsTab.ifSummaryContainerVisible());
    }
    //no screenshot ?????
    @Test
    public void searchFlightFlexibleDatesOneAdultCorrectData() {
        FlightsTab flightsTab = mainPage.openFlightsTab();
        flightsTab.setFlightDeparture("Warsaw Modlin")
                .setFlightDestination("London Stansted")
                .setFlexibleDates()
                .clickDoneAndSearchButton();
        assertTrue(flightsTab.ifDestinationContainerVisible());
    }
    //no screenshot ?????
    @Test
    public void searchFlightFlexibleDatesOneAdultNoResults() {
        FlightsTab flightsTab = mainPage.openFlightsTab();
        flightsTab.setFlightDeparture("Warsaw Modlin")
                .setFlightDestination("Riga")
                .setFlexibleDates()
                .clickDoneAndSearchButton();
        assertTrue(flightsTab.ifNoFlightsIconVisible());
    }
}
