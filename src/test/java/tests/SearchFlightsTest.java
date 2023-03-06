package tests;

import config.TestConfig;
import org.testng.annotations.Test;
import pages.FlightsTab;

import static org.testng.Assert.assertTrue;
import static utility.Actions.getDayAfterTomorrowDate;
import static utility.Actions.getTomorrowDate;

public class SearchFlightsTest extends TestConfig {

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
