package tests;

import com.opencsv.exceptions.CsvException;
import config.TestConfig;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SignupWindow;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static utility.Actions.getDataFromCsv;


public class SignupTest extends TestConfig {


    @DataProvider(name = "wrongPasswords")
    public static Object[][] getData() throws IOException, CsvException {
        return getDataFromCsv("src/test/resources/data/wrong-password-date.csv");
    }

    @Test
    public void signupCorrectCredentialsAccountExist() {
        SignupWindow signupWindow = mainPage.openSignUpWindow();
        signupWindow.signUpWithCredentials("test@qa.team", "TeamTeam1");
        assertEquals(signupWindow.getEmailError(), "User already exists");
    }

    @Test
    public void signupIncorrectEmail() {
        SignupWindow signupWindow = mainPage.openSignUpWindow();
        signupWindow.signUpWithCredentials("test.team", "TeamTeam1");
        assertEquals(signupWindow.getEmailError(), "Invalid email address format");
    }

    @Test
    public void signupWithoutEmail() {
        SignupWindow signupWindow = mainPage.openSignUpWindow();
        signupWindow.signUpWithCredentials("", "TeamTeam1");
        assertEquals(signupWindow.getEmailError(), "Email address is required");
    }


    @Test
    public void signupWithoutPassword() {
        SignupWindow signupWindow = mainPage.openSignUpWindow();
        signupWindow.signUpWithCredentials("test@qa.team", "");
        assertEquals(signupWindow.getPasswordError(), "Password is required");
    }


    @Test(dataProvider = "wrongPasswords")
    @Description("Parameters: arg[0]=one_number_requirement, arg[1]= eight_characters, arg[2]= one_lower, arg[3]= one_upper, arg[4]= password")
    public void signupWithWrongPasswordAndGetPasswordErrors(String one_number_requirement, String eight_characters, String one_lower, String one_upper, String password) {
        SignupWindow signupWindow = mainPage.openSignUpWindow();
        signupWindow.signUpWithCredentials("test@qa.team", password);

        assertEquals("Please check the requirements on your right", signupWindow.getPasswordError());

        List<Boolean> passwordErrorsList = signupWindow.getPasswordErrorsList();

        assertEquals(passwordErrorsList.get(0), Boolean.parseBoolean(one_number_requirement),"At least one number");
        assertEquals(passwordErrorsList.get(1), Boolean.parseBoolean(eight_characters), "At least 8 characters");
        assertEquals(passwordErrorsList.get(2), Boolean.parseBoolean(one_lower), "At least one lower case letter");
        assertEquals(passwordErrorsList.get(3), Boolean.parseBoolean(one_upper), "At least one upper case letter");
    }


}
