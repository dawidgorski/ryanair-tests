package utility;

import config.DriverSingleton;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class RyanAirTestListener implements ITestListener {

    private final String path = "screenshots";

    public void onTestFailure(ITestResult result) {
        System.out.println("test failed- " + result.getName());
        try {
            takeScreenshot(DriverSingleton.getInstance(), result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot(WebDriver driver, String testMethodName) throws IOException {
        Date d = new Date();
        String timeStamp = d.toString().replace(":", "_").replace(" ", "_");

        new File(path).mkdirs();
        FileOutputStream out = new FileOutputStream(path + File.separator + testMethodName + "_" + timeStamp + ".png");
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        out.write(screenshot);
        return screenshot;


    }

}
