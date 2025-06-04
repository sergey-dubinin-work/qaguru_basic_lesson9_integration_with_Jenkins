package guru.qa.demoqa.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import guru.qa.TestBase;
import guru.qa.pages.RegistrationPage;
import guru.qa.pages.RegistrationResultsPage;
import guru.qa.utils.JavaScriptReader;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static guru.qa.helpers.JsonConverter.convertPlainJsonToJsonObject;
import static guru.qa.testData.TestData.userEmail;
import static guru.qa.utils.RandomUtils.getRandomString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PracticeFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationResultsPage registrationResultsPage = new RegistrationResultsPage();

    Faker faker = new Faker();

    @Test
    void testFillingPracticeForm(){

        String
                firstName = getRandomString(12),
                lastName = faker.name().lastName(),
                email = userEmail,
                gender = "Male",
                phoneNumber = "8900500511",
                birthYear = "1994",
                birthMonth = "June",
                birthDay = "30",
                subject = "English",
                hobby = "Sports",
                picturePath = "src/test/resources/img/photo.jpg",
                pictureClassPath = "img/photo.jpg",
                address = "My address",
                state = "Haryana",
                city = "Karnal";


        registrationPage
                .openPage()
                .formHeaderHasText("Student Registration Form")
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .selectGender(gender)
                .typePhoneNumber(phoneNumber)
                .setDateBirthDate(birthYear, birthMonth, birthDay)
                .selectSubject(subject)
                .checkHobby(hobby)
                .selectPicture(picturePath)      // 1st variant
                .selectPictureByClassPath(pictureClassPath)              // 2nd variant
                .typeAddress(address)
                .selectState(state)
                .selectCity(city)
                .clickSubmit()
                ;

        // Assertions

        // Способ 1. Поиск в DOM дереве и сравнение

        String[] splittedPicturePath = picturePath.split("/");

        registrationResultsPage
                .formHeaderHasText("Thanks for submitting the form")
                .submittedFormHasRow("Label", "Values")
                .submittedFormHasRow("Student Name", String.format("%s %s", firstName, lastName))
                .submittedFormHasRow("Student Email", email)
                .submittedFormHasRow("Gender", gender)
                .submittedFormHasRow("Mobile", phoneNumber)
                .submittedFormHasRow("Date of Birth", String.format("%s %s,%s", birthDay, birthMonth, birthYear))
                .submittedFormHasRow("Subjects", subject)
                .submittedFormHasRow("Hobbies", hobby)
                .submittedFormHasRow("Picture", splittedPicturePath[splittedPicturePath.length - 1])
                .submittedFormHasRow("Address", address)
                .submittedFormHasRow("State and City", String.format("%s %s", state, city));

        // Способ 2. Поиск в DOM дереве и сравнение с эталонной Map

        Map<String, String> expectedData = Map.of(
                "Student Name", String.format("%s %s", firstName, lastName),
                "Student Email", email
        );


        // Copy lines to elements collection

        ElementsCollection lines = $$(".table-responsive tbody tr").snapshot();
        for(SelenideElement line: lines){

            String key = line.$("td").text();

            if (expectedData.containsKey(key)){
                String expectedValue = expectedData.get(key);
                line.$("td", 1).shouldHave(exactText(expectedValue));
            }

        }

        // Способ 3. Сохранение данных из таблицы в JSON объект с помощью JS скрипта

        String rawTableData = executeJavaScript(JavaScriptReader.readJavaScriptCodeFromFile("src/test/resources/js/get_table_data.js"));

        JsonObject jsonTableData = convertPlainJsonToJsonObject(rawTableData);
        for(String key: jsonTableData.keySet()){
            if (expectedData.containsKey(key)){
                String expectedValue = expectedData.get(key);
                String actualValue = jsonTableData.get(key).toString();
                assertEquals(expectedValue, actualValue);
            }
        }

        registrationResultsPage
                .clickCloseButton();

        registrationPage
                .formHeaderHasText("Student Registration Form");

    }
}
