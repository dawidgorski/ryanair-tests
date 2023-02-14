package testy;

import config.TestConfig;
import org.junit.jupiter.api.Test;
import pages.SignupWindow;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignupTest extends TestConfig {

    @Test
    public void signupCorrectCredentialsAccountExist() {
        String emailError = mainPage
                .openSignUpWindow()
                .signUpWithCredentials("test@qa.team", "TeamTeam1")
                .getEmailError();
        assertEquals("User already exists", emailError);
    }

    @Test
    public void signupIncorrectEmail() {
        String emailError = mainPage
                .openSignUpWindow()
                .signUpWithCredentials("test.team", "TeamTeam1")
                .getEmailError();
        assertEquals("Invalid email address format", emailError);
    }

    @Test
    public void signupWithoutEmail() {
        String emailError = mainPage
                .openSignUpWindow()
                .signUpWithCredentials("", "TeamTeam1")
                .getEmailError();
        assertEquals("Email address is required", emailError);
    }

    @Test
    public void signupWithoutPassword() {
        String passwordError = mainPage
                .openSignUpWindow()
                .signUpWithCredentials("test@qa.team", "")
                .getPasswordError();
        assertEquals("Password is required", passwordError);
    }

    public void signupWithWrongPasswordAndGetPasswordErrors(String password, boolean one_number_requirement, boolean eight_characters, boolean one_lower, boolean one_upper) {
        SignupWindow signupWindow = mainPage
                .openSignUpWindow()
                .signUpWithCredentials("test@qa.team", password);

        String passwordError = signupWindow.getPasswordError();
        assertEquals("Please check the requirements on your right", passwordError);

        List<Boolean> passwordErrorsList = signupWindow.getPasswordErrorsList();

        assertEquals(one_number_requirement, passwordErrorsList.get(0), "At least one number");
        assertEquals(eight_characters, passwordErrorsList.get(1), "At least 8 characters");
        assertEquals(one_lower, passwordErrorsList.get(2), "At least one lower case letter");
        assertEquals(one_upper, passwordErrorsList.get(3), "At least one upper case letter");
    }

    @Test
    public void signupWithWrongPassword_onlyLowerCase() {
        signupWithWrongPasswordAndGetPasswordErrors("a", false, false, true, false);
    }

    @Test
    public void signupWithWrongPassword_onlyUpperCase() {
        signupWithWrongPasswordAndGetPasswordErrors("A", false, false, false, true);
    }

    @Test
    public void signupWithWrongPassword_onlyNumber() {
        signupWithWrongPasswordAndGetPasswordErrors("1", true, false, false, false);
    }

    @Test
    public void signupWithWrongPassword_OneLowerCaseOneUpper() {
        signupWithWrongPasswordAndGetPasswordErrors("aA", false, false, true, true);
    }

    @Test
    public void signupWithWrongPassword_noNumber() {
        signupWithWrongPasswordAndGetPasswordErrors("TestTest", false, true, true, true);
    }

    @Test
    public void signupWithWrongPassword_noEightCharacters() {
        signupWithWrongPasswordAndGetPasswordErrors("Test1", true, false, true, true);
    }

    @Test
    public void signupWithWrongPassword_noUpperCase() {
        signupWithWrongPasswordAndGetPasswordErrors("eesteeee1", true, true, true, false);
    }

    @Test
    public void signupWithWrongPassword_noLowerCase() {
        signupWithWrongPasswordAndGetPasswordErrors("EAHAHSHASS1", true, true, false, true);
    }

}
