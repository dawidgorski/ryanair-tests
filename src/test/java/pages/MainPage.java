package pages;

import config.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    static WebDriver driver;

    @FindBy(css = "button[aria-label='Log In']")
    private WebElement logInButton;

    @FindBy(css = "button[aria-label='Sign Up']")
    private WebElement signUpButton;

    @FindBy(css = "button[aria-label='flights']")
    private WebElement flightsTAb;

    @FindBy(css = "button[aria-label='Car Hire']")
    private WebElement hireCarTAb;

    @FindBy(css = "button[aria-label='hotels']")
    private WebElement hotelsTab;

    @FindBy(css = "div.ry-header__user-name span")
    private WebElement loggedUserLabel;

    public MainPage() {
        driver = DriverSingleton.getInstance();
        PageFactory.initElements(driver, this);
    }

    public SignupWindow openSignUpWindow() {
        signUpButton.click();
        return new SignupWindow();
    }

    public LoginWindow openLoginWindow() {
        logInButton.click();
        return new LoginWindow();
    }

    public FlightsTab openFlightsTab() {
        flightsTAb.click();
        return new FlightsTab();
    }

    public HireCarTab openHireCarTab() {
        hireCarTAb.click();
        return new HireCarTab();
    }

    public HotelsTab openHotelsTab() {
        hotelsTab.click();
        return new HotelsTab();
    }

    public String getLoggedUserEmail() {
        return loggedUserLabel.getText();
    }
}
