package pages;

import io.qameta.allure.Step;
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

    @Step
    public LoginWindow loginWithCredentials(String email, String password) {
        waitForVisibilityAndSendKeys_webElement(emailTextField, email); //wait for popup loginwindow
        sendKeys_webElement(passwordTextField, password);
        logInConfirmButton.click();
        return this;
    }

    @Step
    public String getEmailError() {
        return emailErrorLabel.getText();
    }

    @Step
    public String getPasswordError() {
        return passwordErrorLabel.getText();
    }

    @Step
    public String getAfterLoginVerificationHeader() {
        return waitForVisibilityAndGetText_webElement(afterLoginVerificationHeader); // wait for header

    }

}
