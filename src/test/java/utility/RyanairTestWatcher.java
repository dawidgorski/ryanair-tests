package utility;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

public class RyanairTestWatcher implements TestWatcher {

    WebDriver driver;
    String path;

    public RyanairTestWatcher(WebDriver driver, String path) {
        this.driver = driver;
        this.path = path;
    }

    public void testDisabled(ExtensionContext context, Optional<String> reason) {
    }

    public void testSuccessful(ExtensionContext context) {
    }

    public void testAborted(ExtensionContext context, Throwable cause) {
    }

    public void testFailed(ExtensionContext context, Throwable cause) {
        captureScreenshot(driver, context.getDisplayName(), "failure");
    }

    public void captureScreenshot(WebDriver driver, String fileName, String action) {
        try {
            new File(path).mkdirs();
            try (FileOutputStream out = new FileOutputStream(path + File.separator + "screenshot-" + action + "-" + fileName + ".png")) {
                out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            }
        } catch (IOException | WebDriverException e) {
            System.out.println("screenshot failed:" + e.getMessage());
        }
    }
}
