package tests;

import config.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.SignupWindow;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignupTest extends TestConfig {


    @Test
    public void signupCorrectCredentialsAccountExist() {
        SignupWindow signupWindow = mainPage.openSignUpWindow();
        signupWindow.signUpWithCredentials("test@qa.team", "TeamTeam1");
        assertEquals("User already exists", signupWindow.getEmailError());
    }

    @Test
    public void signupIncorrectEmail() {
        SignupWindow signupWindow = mainPage.openSignUpWindow();
        signupWindow.signUpWithCredentials("test.team", "TeamTeam1");
        assertEquals("Invalid email address format", signupWindow.getEmailError());
    }

    @Test
    public void signupWithoutEmail() {
        SignupWindow signupWindow = mainPage.openSignUpWindow();
        signupWindow.signUpWithCredentials("", "TeamTeam1");
        assertEquals("Email address is required", signupWindow.getEmailError());
    }
    //no screenshot ?????
    @Test
    public void signupWithoutPassword() {
        SignupWindow signupWindow = mainPage.openSignUpWindow();
        signupWindow.signUpWithCredentials("test@qa.team", "");
        assertEquals("Password is required", signupWindow.getPasswordError());
    }
    //no screenshot ?????
    @ParameterizedTest
    @CsvFileSource(resources = "wrong-password-date.csv")
    public void signupWithWrongPasswordAndGetPasswordErrors( boolean one_number_requirement, boolean eight_characters, boolean one_lower, boolean one_upper, String password) {
        SignupWindow signupWindow = mainPage.openSignUpWindow();
        signupWindow.signUpWithCredentials("test@qa.team", password);

        assertEquals("Please check the requirements on your right", signupWindow.getPasswordError());

        List<Boolean> passwordErrorsList = signupWindow.getPasswordErrorsList();

        assertEquals(one_number_requirement, passwordErrorsList.get(0), "At least one number");
        assertEquals(eight_characters, passwordErrorsList.get(1), "At least 8 characters");
        assertEquals(one_lower, passwordErrorsList.get(2), "At least one lower case letter");
        assertEquals(one_upper, passwordErrorsList.get(3), "At least one upper case letter");
    }


}
