package utility;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

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
        try {
            captureScreenshot(driver, context.getDisplayName(), "failure");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] captureScreenshot(WebDriver driver, String fileName, String action) throws IOException {
        new File(path).mkdirs();
        FileOutputStream out = new FileOutputStream(path + File.separator + "screenshot-" + action + "-" + fileName + ".png");
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        out.write(screenshot);
        return screenshot;

    }
}
