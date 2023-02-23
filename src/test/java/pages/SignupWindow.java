package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static utility.Actions.*;

public class SignupWindow extends MainPage {

    @FindBy(css = "input[name='email']")
    private WebElement emailTextField;

    @FindBy(css = "input[name='password']")
    private WebElement passwordTextField;

    @FindBy(xpath = "//button[contains(text(),'Sign up')]")
    private WebElement signUpConfirmButton;

    @FindBy(css = "ry-input-d[name='email'] span.b2._error")
    private WebElement emailErrorLabel;

    @FindBy(css = "ry-input-d[name='password'] span.b2")
    private WebElement passwordErrorLabel;

    @FindBy(css = "ry-auth-password-validation icon")
    private List<WebElement> passwordErrorsFieldList;

    public SignupWindow() {
        super();
    }

    @Step
    public SignupWindow signUpWithCredentials(String email, String password) {
        waitForVisibilityAndSendKeys_webElement(emailTextField, email); //wait for popup signupwindow
        passwordTextField.sendKeys(password);
        signUpConfirmButton.click();
        return this;
    }

    @Step
    public String getEmailError() {
        return waitForVisibilityAndGetText_webElement(emailErrorLabel); // wait for email error label
    }

    @Step
    public String getPasswordError() {
        return passwordErrorLabel.getText();
    }

    @Step
    public List<Boolean> getPasswordErrorsList() {
        return waitForVisibilityAllElementsAndGetErrorsList_webElement(passwordErrorsFieldList); //wait for password error hint
    }


}
