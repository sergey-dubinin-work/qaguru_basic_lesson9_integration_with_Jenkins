package guru.qa.demoqa.helpers;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static guru.qa.demoqa.config.ConfigurationManager.getConfig;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class DriverUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(DriverUtils.class);

    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }

    public static byte[] getScreenshotAsBytes(){
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static byte[] getPageSourceAsBytes() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    public static String getConsoleLogs(){
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }

    public static URL getVideoUrl(String sessionId){
        String videoUrl = String.format(
                "%s:8080/video/%s.mp4",
                getConfig().selenoidUrl(),
                sessionId
        );

        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            LOGGER.warn("[ALLURE VIDEO ATTACHMENT ERROR] Wrong test video url, {}", videoUrl);
            e.printStackTrace();
        }
        return null;

    }

}
