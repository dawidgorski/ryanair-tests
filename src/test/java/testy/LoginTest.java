package testy;

import config.TestConfig;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utility.Actions.*;

public class LoginTest extends TestConfig {

    @Test
    public void loginCorrectCredentials(){
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='Log In']"));
        waitForVisibilityAndSendKeys(By.cssSelector("input[name='email']"), "test@qa.team");
        waitForVisibilityAndSendKeys(By.cssSelector("input[name='password']"), "TeamTeam1");
        waitForClickabilityAndClick(By.xpath("//button[contains(text(),'Log in')]"));
        waitForVisibilityAndGetText(By.xpath("//h3[@class='auth-popup__header-title']"));
    }

    @Test
    public void loginWithoutCredentials() {
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='Log In']"));
        waitForClickabilityAndClick(By.xpath("//button[contains(text(),'Log in')]"));
        assertEquals("Email address is required", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='email'] span.b2")));
        assertEquals("Password is required", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='password'] span.b2")));
    }

    @Test
    public void loginWithoutPassword() {
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='Log In']"));
        waitForVisibilityAndSendKeys(By.cssSelector("input[name='email']"), "test@qa.team");
        waitForClickabilityAndClick(By.xpath("//button[contains(text(),'Log in')]"));
        assertEquals("Password is required", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='password'] span.b2")));
    }

    @Test
    public void loginWithoutEmail() {
        waitForClickabilityAndClick(By.cssSelector("button[aria-label='Log In']"));
        waitForVisibilityAndSendKeys(By.cssSelector("input[name='password']"), "TeamTeam1");
        waitForClickabilityAndClick(By.xpath("//button[contains(text(),'Log in')]"));
        assertEquals("Email address is required", waitForVisibilityAndGetText(By.cssSelector("ry-input-d[name='email'] span.b2")));

    }


}



