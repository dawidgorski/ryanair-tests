package pages;

import config.DriverSingleton;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utility.Actions.waitForClickabilityAndClick_webElement;

public class MainPage {

    static WebDriver driver;

    @FindBy(css = "button[aria-label='Log In']")
    private WebElement logInButton;

    @FindBy(css = "button[aria-label='Sign Up']")
    private WebElement signUpButton;

    @FindBy(css = "button[aria-label='flights']")
    private WebElement flightsTab;

    @FindBy(css = "button[aria-label='Car Hire']")
    private WebElement hireCarTab;

    @FindBy(css = "button[aria-label='hotels']")
    private WebElement hotelsTab;

    @FindBy(css = "div.ry-header__user-name span")
    private WebElement loggedUserLabel;

    public MainPage() {
        driver = DriverSingleton.getInstance();
        PageFactory.initElements(driver, this);
    }

    @Step
    public SignupWindow openSignUpWindow() {
        signUpButton.click();

        return new SignupWindow();
    }

    @Step
    public LoginWindow openLoginWindow() {
        logInButton.click();
        return new LoginWindow();
    }

    @Step
    public FlightsTab openFlightsTab() {
        flightsTab.click();
        return new FlightsTab();
    }

    @Step
    public HireCarTab openHireCarTab() {
        hireCarTab.click();
        return new HireCarTab();
    }

    @Step
    public HotelsTab openHotelsTab() {
        hotelsTab.click();
        return new HotelsTab();
    }

    @Step
    public String getLoggedUserEmail() {
        return loggedUserLabel.getText();
    }
}
