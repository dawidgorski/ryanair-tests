package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static utility.Actions.*;

public class FlightsTab extends MainPage{

    @FindBy(id = "input-button__destination")
    WebElement destinationButton;

    @FindBy(id = "input-button__departure")
    WebElement departureButton;

    @FindBy(xpath = "//span[contains(text(),'Flexible dates')]")
    WebElement flexibleDatesTab;

    @FindBy(css = "[data-ref='flexible-dates__month-item']")
    List<WebElement> monthsList;

    @FindBy(css = "[data-ref='flexible-dates__day-item']")
    List<WebElement> dayOfTheWeekList;

    @FindBy(css = "[aria-label='Apply']")
    WebElement applyButton;

    @FindBy(css = "button[aria-label=Done]")
    WebElement doneButton;

    @FindBy(css = "button[data-ref=flight-search-widget__cta]")
    WebElement searchButton;

    @FindBy(css = "flights-summary-container")
    WebElement summaryContainer;

    @FindBy(css = ".destination__container")
    WebElement destinationContainer;

    @FindBy(css = "[iconid='glyphs/no-flights']")
    private WebElement noFlightsIcon;

    @FindBy(css = "calendar[class='datepicker__calendar datepicker__calendar--left'] div[tabindex]")
    private WebElement firstAvailableDepartureDate;

    @FindBy(css = "calendar[class='datepicker__calendar ng-star-inserted'] div[tabindex]")
    private WebElement firstAvailableArrivalDate;

    public FlightsTab() {
        super();
    }

    @Step
    public FlightsTab setFlightDestination(String airport) {
        destinationButton.sendKeys(airport);
        waitForClickabilityAndClick(By.xpath("//span[contains(text(),'" + airport + "')]"));
        return this;
    }

    @Step
    public FlightsTab setFlightDeparture(String airport) {
        sendKeys_webElement(departureButton, airport);
        waitForClickabilityAndClick(By.xpath("//span[contains(text(),'" + airport + "')]"));
        return this;
    }

    @Step
    public FlightsTab setExactDates(String departureDate, String returnDate) {
        waitForClickabilityAndClick(By.cssSelector("[class='datepicker__calendar datepicker__calendar--left'] [data-id='" + departureDate + "'] "));
        waitForClickabilityAndClick(By.cssSelector("[class='datepicker__calendar datepicker__calendar--left'] [data-id='" + returnDate + "'] "));
        return this;
    }
    @Step
    public FlightsTab setFirstAvailableDates() {
        waitForClickabilityAndClick_webElement(firstAvailableDepartureDate);
        waitForClickabilityAndClick_webElement(firstAvailableDepartureDate);
        return this;
    }

    @Step
    public FlightsTab setFlexibleDates() {
        waitForClickabilityAndClick_webElement(flexibleDatesTab); //wait for open calendar
        monthsList.get(0).click();
        dayOfTheWeekList.get(0).click();
        applyButton.click();
        return this;
    }

    @Step
    public FlightsTab clickDoneAndSearchButton() {
        doneButton.click();
        searchButton.click();
        return this;
    }

    @Step
    public boolean ifSummaryContainerVisible() {
        return waitForVisibility_webElement(summaryContainer);
    }

    @Step
    public boolean ifDestinationContainerVisible() {
        return waitForVisibility(By.cssSelector(".destination__container"));
    }

    @Step
    public boolean ifNoFlightsIconVisible() {
        return waitForVisibility_webElement(noFlightsIcon);
    }

}
