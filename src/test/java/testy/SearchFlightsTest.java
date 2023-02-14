package testy;

import config.TestConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static utility.Actions.getDayAfterTomorrowDate;
import static utility.Actions.getTomorrowDate;

public class SearchFlightsTest extends TestConfig {

    @Test
    public void searchFlightExactDatesTodayTomorrowOneAdultCorrectData() {
        boolean flightSummaryVisible = mainPage
                .openFlightsTab()
                .setFlightDeparture("Warsaw Modlin")
                .setFlightDestination("London Stansted")
                .setExactDates(getTomorrowDate(), getDayAfterTomorrowDate())
                .clickDoneAndSearchButton()
                .ifSummaryContainerVisible();
        assertTrue(flightSummaryVisible);
    }

    @Test
    public void searchFlightFlexibleDatesOneAdultCorrectData() {
        boolean destinationContainerVisible = mainPage
                .openFlightsTab()
                .setFlightDeparture("Warsaw Modlin")
                .setFlightDestination("London Stansted")
                .setFlexibleDates()
                .clickDoneAndSearchButton()
                .ifDestinationContainerVisible();
        assertTrue(destinationContainerVisible);
    }

    @Test
    public void searchFlightFlexibleDatesOneAdultNoResults() {
        boolean noFlightsIconVisible = mainPage
                .openFlightsTab()
                .setFlightDeparture("Warsaw Modlin")
                .setFlightDestination("Riga")
                .setFlexibleDates()
                .clickDoneAndSearchButton()
                .ifNoFlightsIconVisible();
        assertTrue(noFlightsIconVisible);
    }
}
