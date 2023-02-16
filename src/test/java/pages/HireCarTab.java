package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utility.Actions.*;

public class HireCarTab extends MainPage {

    @FindBy(css = "#input-button__pick-up-location")
    private WebElement pickUpLocationTextField;

    @FindBy(css = "div.tooltip-inner [classlist='icon-18']")
    private WebElement firstLocationResult;

    @FindBy(css = "button[aria-label=\"Let's go\"]")
    private WebElement submitSearchCarButton;

    @FindBy(css = "div.car-hire-container__header")
    private WebElement carResultsContainer;

    public HireCarTab() {
        super();
    }

    public HireCarTab searchCarDefaultDates(String location) {
        waitForVisibilityAndSendKeys_webElement(pickUpLocationTextField, location);
        waitForClickabilityAndClick_webElement(firstLocationResult);
        waitForClickabilityAndClick_webElement(submitSearchCarButton);
        return this;
    }

    public boolean isResultsContainerAvailable() {
        return waitForVisibility_webElement(carResultsContainer);
    }

}
