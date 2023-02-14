package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utility.Actions.*;

public class HotelsTab extends MainPage {

    @FindBy(id = "input-button__locations-or-properties")
    WebElement destinationOrPropertyTextField;

    @FindBy(css = "div.tooltip-inner [classlist='icon-14']")
    WebElement dropDownFirstResult;

    @FindBy(css = "[uniqueid='check-in'")
    WebElement checkInDateDropDownMenu;

    @FindBy(css = "[uniqueid='check-in'] [data-ref='input-button__error']")
    WebElement checkInErrorLabel;

    @FindBy(css = "button[aria-label='Search']")
    WebElement searchButton;

    @FindBy(css = "rooms-list")
    WebElement roomsList;

    public HotelsTab enterLocationOrProperty(String locationOrProperty) {
        waitForVisibilityAndSendKeys_webElement(destinationOrPropertyTextField, locationOrProperty);
        waitForClickabilityAndClick_webElement(dropDownFirstResult);
        return this;
    }

    public HotelsTab enterSimpleCheckInAndCheckout(String todayDate, String tomorrowDate) {
        waitForClickabilityAndClick_webElement(checkInDateDropDownMenu);
        waitForClickabilityAndClick(By.cssSelector("[role='tooltip'] [data-id='" + todayDate + "']"));
        waitForClickabilityAndClick(By.cssSelector("[role='tooltip'] [data-id='" + tomorrowDate + "']"));
        return this;
    }

    public String getCheckInError() {
        return waitForVisibilityAndGetText_webElement(checkInErrorLabel);
    }

    public HotelsTab clickSearchButton() {
        waitForClickabilityAndClick_webElement(searchButton);
        return this;
    }

    public boolean checkVisibilityOfRoomslist() {
        return waitForVisibility_webElement(roomsList);
    }


}
