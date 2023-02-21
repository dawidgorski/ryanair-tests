package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utility.Actions.*;

public class LoginWindow extends MainPage {

    @FindBy(css = "input[name='email']")
    private WebElement emailTextField;

    @FindBy(css = "input[name='password']")
    private WebElement passwordTextField;

    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    private WebElement logInConfirmButton;

    @FindBy(css = "ry-input-d[name='email'] span.b2")
    private WebElement emailErrorLabel;

    @FindBy(css = "ry-input-d[name='password'] span.b2")
    private WebElement passwordErrorLabel;

    @FindBy(xpath = "//h3[contains(text(),'Register this device')]")
    private WebElement afterLoginVerificationHeader;

    public LoginWindow() {
        super();
    }

    public LoginWindow loginWithCredentials(String email, String password) {
        waitForVisibilityAndSendKeys_webElement(emailTextField, email); //wait for popup loginwindow
        sendKeys_webElement(passwordTextField, password);
        logInConfirmButton.click();
        return this;
    }

    public String getEmailError() {
        return emailErrorLabel.getText();
    }

    public String getPasswordError() {
        return passwordErrorLabel.getText();
    }

    public String getAfterLoginVerificationHeader() {
        return waitForVisibilityAndGetText_webElement(afterLoginVerificationHeader); // wait for header

    }

}
