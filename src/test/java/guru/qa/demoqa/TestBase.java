package guru.qa.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.demoqa.helpers.AllureAttachments;
import guru.qa.demoqa.helpers.DriverUtils;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static guru.qa.demoqa.config.ConfigurationManager.getConfig;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = String.format(
                "%s:%s/wd/hub",
                getConfig().selenoidUrl(),
                getConfig().selenoidPort()
                );

        ChromeOptions options = new ChromeOptions();

        options.setCapability("selenoid:options", new HashMap<String, Object>() {
                    {
                        put("enableVNC", true);
                        put("enableVideo", true);
                    }
                }
        );

        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);


    }

    @AfterEach
    void tearDown() {
        String sessionId = DriverUtils.getSessionId();

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
        AllureAttachments.addBrowserConsoleLogs();

        closeWebDriver();

        AllureAttachments.addVideo(sessionId);
        AllureAttachments.addAutoplayVideo(sessionId);
    }

}
