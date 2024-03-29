package tests;

import config.TestConfig;
import org.testng.annotations.Test;
import pages.FlightsTab;

import static org.testng.Assert.assertTrue;
public class SearchFlightsTest extends TestConfig {

    @Test
    public void searchFlightExactDateOneAdultCorrectData() {
        FlightsTab flightsTab = mainPage.openFlightsTab();
        flightsTab
                .setFlightDeparture("Warsaw Modlin")
                .setFlightDestination("London Stansted")
                .setFirstAvailableDates()
                .clickDoneAndSearchButton();
        assertTrue(flightsTab.ifSummaryContainerVisible());
    }

    @Test
    public void searchFlightFlexibleDatesOneAdultCorrectData() {
        FlightsTab flightsTab = mainPage.openFlightsTab();
        flightsTab.setFlightDeparture("Warsaw Modlin")
                .setFlightDestination("London Stansted")
                .setFlexibleDates()
                .clickDoneAndSearchButton();
        assertTrue(flightsTab.ifDestinationContainerVisible());
    }

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
