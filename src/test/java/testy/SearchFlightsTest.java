package testy;

import config.TestConfig;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static utility.Actions.*;

public class SearchFlightsTest extends TestConfig {

    @Test
    public void openPage() {
    }


    private void setFlightDestination(String airport) {
        waitForVisibilityAndSendKeys(By.id("input-button__destination"), airport);
        waitForClickabilityAndClick(By.xpath("//span[contains(text(),'" + airport + "')]"));
    }

    private void setFlightDeparture(String airport) {
        waitForVisibilityAndSendKeys(By.id("input-button__departure"), airport);
        waitForClickabilityAndClick(By.xpath("//span[contains(text(),'" + airport + "')]"));
    }

    @Test
    public void searchFlightExactDatesTodayTomorrowOneAdultCorrectData() {
        setFlightDeparture("Warsaw Modlin");
        setFlightDestination("London Stansted");
        waitForClickabilityAndClick(By.cssSelector("[class='datepicker__calendar datepicker__calendar--left'] [data-id='" + getTodayDate() + "'] "));
        waitForClickabilityAndClick(By.cssSelector("[class='datepicker__calendar datepicker__calendar--left'] [data-id='" + getTomorrowDate() + "'] "));
        waitForClickabilityAndClick(By.cssSelector("button[aria-label=Done]"));
        waitForClickabilityAndClick(By.cssSelector("button[data-ref=flight-search-widget__cta]"));
        assertTrue(waitForVisibility(By.cssSelector("flights-summary-container")));

    }

    @Test
    public void searchFlightFlexibleDatesOneAdultCorrectData() {
        setFlightDeparture("Warsaw Modlin");
        setFlightDestination("London Stansted");

        waitForClickabilityAndClick(By.xpath("//span[contains(text(),'Flexible dates')]"));
        waitForClickabilityAndClick(By.cssSelector("[data-ref='flexible-dates__month-item']"));
        waitForClickabilityAndClick(By.cssSelector("[data-ref='flexible-dates__day-item']"));
        waitForClickabilityAndClick(By.cssSelector("[aria-label='Apply'] "));
        waitForClickabilityAndClick(By.cssSelector("button[aria-label=Done]"));
        waitForClickabilityAndClick(By.cssSelector("button[data-ref=flight-search-widget__cta]"));
        assertTrue(waitForVisibility(By.cssSelector(".destination__container")));


    }

    @Test
    public void searchFlightFlexibleDatesOneAdultNoResults() {
        setFlightDeparture("Warsaw Modlin");
        setFlightDestination("Riga");
        waitForClickabilityAndClick(By.xpath("//span[contains(text(),'Flexible dates')]"));
        waitForClickabilityAndClick(By.cssSelector("[data-ref='flexible-dates__month-item']"));
        waitForClickabilityAndClick(By.cssSelector("[data-ref='flexible-dates__day-item']"));
        waitForClickabilityAndClick(By.cssSelector("[aria-label='Apply'] "));
        waitForClickabilityAndClick(By.cssSelector("button[aria-label=Done]"));
        waitForClickabilityAndClick(By.cssSelector("button[data-ref=flight-search-widget__cta]"));
        assertTrue(waitForVisibility(By.cssSelector("[iconid='glyphs/no-flights']")));

    }
}
