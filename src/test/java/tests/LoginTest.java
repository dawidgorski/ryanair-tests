package tests;

import config.TestConfig;
import org.testng.annotations.Test;
import pages.LoginWindow;

import static org.testng.Assert.assertEquals;


public class LoginTest extends TestConfig {

    String correctEmail = "test@qa.team";
    String correctPassword = "TeamTeam1";

    @Test
    public void loginCorrectCredentials() {
        LoginWindow loginWindow = mainPage
                .openLoginWindow()
                .loginWithCredentials(correctEmail, correctPassword);
        assertEquals("Register this device", loginWindow.getAfterLoginVerificationHeader());
    }

    @Test
    public void loginWithoutCredentials() {
        LoginWindow loginWindow = mainPage
                .openLoginWindow()
                .loginWithCredentials("", "");
        assertEquals("Email address is required", loginWindow.getEmailError());
        assertEquals("Password is required", loginWindow.getPasswordError());
    }

    @Test
    public void loginWithoutPassword() {
        LoginWindow loginWindow = mainPage
                .openLoginWindow()
                .loginWithCredentials(correctEmail, "");
        assertEquals("Password is required", loginWindow.getPasswordError());
    }

    @Test
    public void loginWithoutEmail() {
        LoginWindow loginWindow = mainPage
                .openLoginWindow()
                .loginWithCredentials("", correctPassword);
        assertEquals("Email address is required", loginWindow.getEmailError());
    }

}



