package utility;

import config.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class RyanAirTestListener implements ITestListener {

    public void onTestFailure(ITestResult result) {
        System.out.println("test failed- " + result.getName());
        try {
            takeScreenshot(DriverSingleton.getInstance(), result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeScreenshot(WebDriver driver, String testMethodName) throws IOException {
        Date d = new Date();
        String TimeStamp = d.toString().replace(":", "_").replace(" ", "_");
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("./screenshots/" + testMethodName + "_" + TimeStamp + ".png"));
    }
}
