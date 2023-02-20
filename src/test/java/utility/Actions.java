package utility;

import config.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Actions {

    static WebDriverWait wait;
    
    private static WebDriverWait getWebDriverWaitInstance(){
        return new WebDriverWait(DriverSingleton.getInstance(), Duration.ofSeconds(5));
    }

    public static boolean waitForVisibility(By by) {
        wait = getWebDriverWaitInstance();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
    }

    public static boolean waitForVisibility_webElement(WebElement element) {
        wait = getWebDriverWaitInstance();
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public static void waitForVisibilityAndSendKeys(By by, String keys) {
        wait = getWebDriverWaitInstance();
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(Keys.CONTROL + "a");
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(keys);
    }

    public static void waitForVisibilityAndSendKeys_webElement(WebElement element, String keys) {
        wait = getWebDriverWaitInstance();
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(Keys.CONTROL + "a");
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(keys);
    }

    public static void waitForClickabilityAndClick(By by) {
        wait = getWebDriverWaitInstance();
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public static void waitForClickabilityAndClick_webElement(WebElement element) {
        wait = getWebDriverWaitInstance();
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static String waitForVisibilityAndGetText(By by) {
        wait = getWebDriverWaitInstance();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
    }

    public static String waitForVisibilityAndGetText_webElement(WebElement element) {
        wait = getWebDriverWaitInstance();
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public static List<String> waitForVisibilityAllElementsAndGetTextList(By by) {
        wait = getWebDriverWaitInstance();
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by))
                .stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public static List<Boolean> waitForVisibilityAllElementsAndGetErrorsList(By by) {
        wait = getWebDriverWaitInstance();
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by))
                .stream().map(e -> e.getAttribute("class"))
                .map(e -> !e.equals("icon--error"))
                .collect(Collectors.toList());
    }

    public static List<Boolean> waitForVisibilityAllElementsAndGetErrorsList_webElement(List<WebElement> webElements) {
        wait = getWebDriverWaitInstance();
        return wait.until(ExpectedConditions.visibilityOfAllElements(webElements))
                .stream().map(e -> e.getAttribute("class"))
                .map(e -> !e.equals("icon--error"))
                .collect(Collectors.toList());
    }

    public static WebElement waitForVisibilityOfElement(By by) {
        wait = getWebDriverWaitInstance();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static String getTodayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getTomorrowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(new Date().getTime() + 86400000);
        return formatter.format(date);
    }

    public static String getDayAfterTomorrowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(new Date().getTime() + 2 * 86400000);
        return formatter.format(date);
    }

    public static WebElement waitAndSwitchForFrame(By by) {
        wait = getWebDriverWaitInstance();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void scrollDown(String cssSelector) {
        JavascriptExecutor js = (JavascriptExecutor) DriverSingleton.getInstance();
        js.executeScript("document.querySelector('" + cssSelector + "').scrollTop=200");
    }

}
