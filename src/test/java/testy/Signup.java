package testy;

import config.DriverSingleton;
import config.TestConfig;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utility.Actions.*;

public class Signup extends TestConfig {

    @Test
    public void signupCorrectCredentials() {
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='Sign Up']"));
        waitForVisibilityAndSendKeys(By.cssSelector("input[name='email']"), "test@qa.team");
        waitForVisibilityAndSendKeys(By.cssSelector("input[name='password']"), "TeamTeam1");
        waitForClickabilityAndClick(By.xpath("//button[contains(text(),'Sign up')]"));
//        Assert.assertEquals("Verify",waitForVisibilityAndGetText(By.xpath("//h3[@class='auth-popup__header-title']")));
    }

    @Test
    public void signupIncorrectEmail() {
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='Sign Up']"));
        waitForVisibilityAndSendKeys(By.cssSelector("input[name='email']"), "test.team");
        waitForVisibilityAndSendKeys(By.cssSelector("input[name='password']"), "TeamTeam1");
        waitForClickabilityAndClick(By.xpath("//button[contains(text(),'Sign up')]"));
        assertEquals("Invalid email address format", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='email'] span.b2")));
    }

    @Test
    public void signupWithoutEmail() {
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='Sign Up']"));
        waitForVisibilityAndSendKeys(By.cssSelector("input[name='password']"), "TeamTeam1");
        waitForClickabilityAndClick(By.xpath("//button[contains(text(),'Sign up')]"));
        assertEquals("Email address is required", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='email'] span.b2")));
    }

    @Test
    public void signupWithoutPassword() {
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='Sign Up']"));
        waitForVisibilityAndSendKeys(By.cssSelector("input[name='email']"), "test@qa.team");
        waitForClickabilityAndClick(By.xpath("//button[contains(text(),'Sign up')]"));
        assertEquals("Password is required", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='password'] span.b2")));
    }

    public void signupWithWrongPassword(String password, boolean one_number_requirement, boolean eight_characters, boolean one_lower, boolean one_upper) {
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='Sign Up']"));
        waitForVisibilityAndSendKeys(By.cssSelector("input[name='email']"), "test@qa.team");
        waitForVisibilityAndSendKeys(By.cssSelector("input[name='password']"), password);
        waitForClickabilityAndClick(By.xpath("//button[contains(text(),'Sign up')]"));
        List<Boolean> errors_thrown = waitForVisibilityAllElementsAndGetErrorsList(By.cssSelector("ry-auth-password-validation icon"));

        assertEquals(one_number_requirement, errors_thrown.get(0), "At least one number");
        assertEquals(eight_characters, errors_thrown.get(1), "At least 8 characters");
        assertEquals(one_lower, errors_thrown.get(2), "At least one lower case letter");
        assertEquals(one_upper, errors_thrown.get(3), "At least one upper case letter");
    }

    @Test
    public void signupWithWrongPassword_onlyLowerCase() {
        signupWithWrongPassword("a", false, false, true, false);
        assertEquals("Please check the requirements on your right", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='password'] span.b2")));
    }

    @Test
    public void signupWithWrongPassword_onlyUpperCase() {
        signupWithWrongPassword("A", false, false, false, true);
        assertEquals("Please check the requirements on your right", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='password'] span.b2")));
    }

    @Test
    public void signupWithWrongPassword_onlyNumber() {
        signupWithWrongPassword("1", true, false, false, false);
        assertEquals("Please check the requirements on your right", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='password'] span.b2")));
    }

    @Test
    public void signupWithWrongPassword_OneLowerCaseOneUpper() {
        signupWithWrongPassword("aA", false, false, true, true);
        assertEquals("Please check the requirements on your right", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='password'] span.b2")));
    }

    @Test
    public void signupWithWrongPassword_noNumber() {
        signupWithWrongPassword("TestTest", false, true, true, true);
        assertEquals("Please check the requirements on your right", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='password'] span.b2")));
    }

    @Test
    public void signupWithWrongPassword_noEightCharacters() {
        signupWithWrongPassword("Test1", true, false, true, true);
        assertEquals("Please check the requirements on your right", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='password'] span.b2")));
    }

    @Test
    public void signupWithWrongPassword_noUpperCase() {
        signupWithWrongPassword("eesteeee1", true, true, true, false);
        assertEquals("Please check the requirements on your right", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='password'] span.b2")));
    }

    @Test
    public void signupWithWrongPassword_noLowerCase() {
        signupWithWrongPassword("EAHAHSHASS1", true, true, false, true);
        assertEquals("Please check the requirements on your right", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='password'] span.b2")));
    }

}
