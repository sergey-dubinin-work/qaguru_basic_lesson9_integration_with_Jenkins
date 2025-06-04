package guru.qa.demoqa.helpers;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.sleep;

public class AllureAttachments {

    public static final Logger LOGGER = LoggerFactory.getLogger(DriverUtils.class);

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] addScreenshotAs(String attachName) {
        return DriverUtils.getScreenshotAsBytes();
    }

    @Attachment(value = "Page source", type = "text/html", fileExtension = "html")
    public static byte[] addPageSource() {
        return DriverUtils.getPageSourceAsBytes();
    }

    public static void addBrowserConsoleLogs() {
        attachAsText(
                "Browser console logs",
                DriverUtils.getConsoleLogs()
        );
    }

    public static void addVideo(String sessionId){
        URL videoUrl = DriverUtils.getVideoUrl(sessionId);
        if (videoUrl != null) {
            InputStream videoInputStream = null;

            for (int i = 0; i < 10; i++) {
                try {
                    videoInputStream = videoUrl.openStream();
                    break;
                } catch (FileNotFoundException e) {
                    sleep(1000);
                } catch (IOException e) {
                    LOGGER.warn("[ALLURE VIDEO ATTACHMENT ERROR] Cant attach allure video, {}", videoUrl);
                    e.printStackTrace();
                }
            }
            if (videoInputStream != null){
                Allure.addAttachment(
                        "Video",
                        "video/mp4",
                        videoInputStream,
                        "mp4"
                );
            }

        }

    }

    @Attachment(value = "Autoplay video", type = "text/html", fileExtension = ".html")
    public static String addAutoplayVideo(String sessionId) {

        return loadTemplate("templatesHTML/autoplayVideo.html").replace(
                "{{video_url}}",
                DriverUtils.getVideoUrl(sessionId).toString()
        );

    }

    public static String loadTemplate(String templatePath) {
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(templatePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Template file not found: " + templatePath);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load template: " + templatePath, e);
        }
    }

}
