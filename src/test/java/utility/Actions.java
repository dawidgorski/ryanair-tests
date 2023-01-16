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

    public static boolean waitForVisibility(By by){
        wait = new WebDriverWait(DriverSingleton.getInstance(), Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
    }

    public static void waitForVisibilityAndSendKeys(By by,String keys) {
        wait = new WebDriverWait(DriverSingleton.getInstance(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(Keys.CONTROL+"a");
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(keys);
    }

    public static void waitForClickabilityAndClick(By by){
        wait = new WebDriverWait(DriverSingleton.getInstance(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public static String waitForVisibilityAndGetText(By by){
        wait = new WebDriverWait(DriverSingleton.getInstance(), Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
    }

    public static List<String> waitForVisibilityAllElementsAndGetTextList(By by){
        wait = new WebDriverWait(DriverSingleton.getInstance(), Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by))
                .stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public static List<Boolean> waitForVisibilityAllElementsAndGetErrorsList(By by){
        wait = new WebDriverWait(DriverSingleton.getInstance(), Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by))
                .stream().map(e->e.getAttribute("class"))
                .map(e -> !e.equals("icon--error"))
                .collect(Collectors.toList());
    }

    public static WebElement waitForVisibilityOfElement(By by){
        wait = new WebDriverWait(DriverSingleton.getInstance(), Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }

    public static String getTodayDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getTomorrowDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(new Date().getTime() + 86400000);
        return formatter.format(date);
    }

    public void scrollDown(String cssSelector) {
        JavascriptExecutor js = (JavascriptExecutor) DriverSingleton.getInstance();
        js.executeScript("document.querySelector('" + cssSelector + "').scrollTop=200");
    }

}
