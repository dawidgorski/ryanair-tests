package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utility.Actions.*;

public class HotelsTab extends MainPage{

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

    public HotelsTab() {
       super();
    }

    @Step
    public HotelsTab enterLocationOrProperty(String locationOrProperty) {
        waitForVisibilityAndSendKeys_webElement(destinationOrPropertyTextField, locationOrProperty); //wait for hotelsTab tab
        waitForClickabilityAndClick_webElement(dropDownFirstResult); //wait for dropdown menu
        return this;
    }

    @Step
    public HotelsTab enterSimpleCheckInAndCheckout(String todayDate, String tomorrowDate) {
        checkInDateDropDownMenu.click();
        waitForClickabilityAndClick(By.cssSelector("[role='tooltip'] [data-id='" + todayDate + "']"));
        waitForClickabilityAndClick(By.cssSelector("[role='tooltip'] [data-id='" + tomorrowDate + "']"));
        return this;
    }

    @Step
    public String getCheckInError() {
        return waitForVisibilityAndGetText_webElement(checkInErrorLabel);
    }

    @Step
    public HotelsTab clickSearchButton() {
        searchButton.click();
        return this;
    }

    @Step
    public boolean checkVisibilityOfRoomslist() {
        return waitForVisibility_webElement(roomsList);
    }


}
