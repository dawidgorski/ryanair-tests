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
        assertEquals(loginWindow.getAfterLoginVerificationHeader(), "Register this device");
    }

    @Test
    public void loginWithoutCredentials() {
        LoginWindow loginWindow = mainPage
                .openLoginWindow()
                .loginWithCredentials("", "");
        assertEquals(loginWindow.getEmailError(),"Email address is required");
        assertEquals(loginWindow.getPasswordError(), "Password is required");
    }

    @Test
    public void loginWithoutPassword() {
        LoginWindow loginWindow = mainPage
                .openLoginWindow()
                .loginWithCredentials(correctEmail, "");
        assertEquals(loginWindow.getPasswordError(), "Password is required");
    }

    @Test
    public void loginWithoutEmail() {
        LoginWindow loginWindow = mainPage
                .openLoginWindow()
                .loginWithCredentials("", correctPassword);
        assertEquals(loginWindow.getEmailError(), "Email address is required");
    }

}



