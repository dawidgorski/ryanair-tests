package pages;

import config.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utility.Actions.waitForClickabilityAndClick_webElement;
import static utility.Actions.waitForVisibilityAndGetText_webElement;

public class MainPage {

    static WebDriver driver;

    @FindBy(css = "button[aria-label='Log In']")
    private WebElement logInButton;

    @FindBy(css = "div.ry-header__user-name span")
    private WebElement loggedUserField;

    public MainPage() {
        driver = DriverSingleton.getInstance();
        PageFactory.initElements(driver, this);
    }

    public LoginWindow openLoginWindow(){
        waitForClickabilityAndClick_webElement(logInButton);
        return new LoginWindow();
    }

    public String getLoggedUserEmail(){
        return waitForVisibilityAndGetText_webElement(loggedUserField);
    }
}
