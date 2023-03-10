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


    @Test
    public void signupWithoutPassword() {
        SignupWindow signupWindow = mainPage.openSignUpWindow();
        signupWindow.signUpWithCredentials("test@qa.team", "");
        assertEquals("Password is required", signupWindow.getPasswordError());
    }


    @Test(dataProvider = "wrongPasswords")
    @Description("Parameters: arg[0]=one_number_requirement, arg[1]= eight_characters, arg[2]= one_lower, arg[3]= one_upper, arg[4]= password")
    public void signupWithWrongPasswordAndGetPasswordErrors(String one_number_requirement, String eight_characters, String one_lower, String one_upper, String password) {
        SignupWindow signupWindow = mainPage.openSignUpWindow();
        signupWindow.signUpWithCredentials("test@qa.team", password);

        assertEquals("Please check the requirements on your right", signupWindow.getPasswordError());

        List<Boolean> passwordErrorsList = signupWindow.getPasswordErrorsList();

        assertEquals(Boolean.parseBoolean(one_number_requirement), passwordErrorsList.get(0), "At least one number");
        assertEquals(Boolean.parseBoolean(eight_characters), passwordErrorsList.get(1), "At least 8 characters");
        assertEquals(Boolean.parseBoolean(one_lower), passwordErrorsList.get(2), "At least one lower case letter");
        assertEquals(Boolean.parseBoolean(one_upper), passwordErrorsList.get(3), "At least one upper case letter");
    }


}
