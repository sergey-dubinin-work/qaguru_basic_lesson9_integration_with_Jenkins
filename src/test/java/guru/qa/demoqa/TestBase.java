package guru.qa.demoqa;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "http://localhost:4444/wd/hub";

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

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
    }

}
