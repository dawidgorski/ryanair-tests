package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utility.Actions.*;

public class LoginWindow extends MainPage{

    @FindBy(css = "input[name='email']")
    private WebElement emailTextField;

    @FindBy(css = "input[name='password']")
    private WebElement passwordTextField;

    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    private WebElement logInConfirmButton;

    @FindBy(css = "ry-input-d[name='email'] span.b2")
    private WebElement emailErrorField;

    @FindBy(css = "ry-input-d[name='password'] span.b2")
    private WebElement passwordErrorField;

    public LoginWindow() {
        super();
    }

    public LoginWindow loginWithCredentials(String email, String password){
        waitForVisibilityAndSendKeys_webElement(emailTextField, email);
        waitForVisibilityAndSendKeys_webElement(passwordTextField, password);
        return this;
    }

    public String getEmailError(){
        return waitForVisibilityAndGetText_webElement(emailErrorField);
    }

    public String getPasswordError(){
        return waitForVisibilityAndGetText_webElement(passwordErrorField);
    }

    public LoginWindow submitLoginForm(){
        logInConfirmButton.click();
        return this;
    }

}
