package guru.qa.demoqa.tests;

import com.codeborne.selenide.Selenide;
import guru.qa.demoqa.TestBase;
import guru.qa.demoqa.pages.RegistrationPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JSExecutionTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void checkJSExecution() throws IOException {

        registrationPage
                .openPage();

        String jsScript = Files.readString(Path.of("src/test/resources/js/alert.js"));

        Selenide.executeJavaScript(jsScript);

        Alert alert = Selenide.switchTo().alert();

        assertEquals("Test alert message", alert.getText());

        Selenide.confirm();

    }
}
