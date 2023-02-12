package testy;

import config.TestConfig;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginWindow;
import pages.MainPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utility.Actions.*;

public class LoginTest extends TestConfig {

    String correctEmail = "test@qa.team";
    String correctPassword = "TeamTeam1";

    @Test
    public void loginCorrectCredentials() {


        MainPage mainPage = new MainPage();
        String loggerUserEmail = mainPage
                .openLoginWindow()
                .loginWithCredentials(correctEmail, correctPassword)
                .submitLoginForm()
                .getLoggedUserEmail();
        assertEquals(correctEmail, loggerUserEmail);
    }

    @Test
    public void loginWithoutCredentials() {
        MainPage mainPage = new MainPage();
        LoginWindow loginWindow = mainPage
                .openLoginWindow()
                .loginWithCredentials("", "")
                .submitLoginForm();
        assertEquals("Email address is required", loginWindow.getEmailError());
        assertEquals("Password is required", loginWindow.getPasswordError());
    }

    @Test
    public void loginWithoutPassword() {
        MainPage mainPage = new MainPage();
        LoginWindow loginWindow = mainPage
                .openLoginWindow()
                .loginWithCredentials(correctEmail, "")
                .submitLoginForm();
        assertEquals("Password is required", loginWindow.getPasswordError());
    }

    @Test
    public void loginWithoutEmail() {
        MainPage mainPage = new MainPage();
        LoginWindow loginWindow = mainPage
                .openLoginWindow()
                .loginWithCredentials("", correctPassword)
                .submitLoginForm();
        assertEquals("Email address is required", loginWindow.getEmailError());
    }


}



