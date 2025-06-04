package guru.qa.demoqa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.demoqa.pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    SelenideElement
            formHeader = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput =  $("#userEmail"),
            genderRadio = $("#genterWrapper"),
            phoneNumberInput = $("#userNumber"),
            birthdaySelect = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbiesCheckBox = $("#hobbiesWrapper"),
            uploadPictureButton = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateDropDown = $("#state"),
            cityDropDown = $("#city"),
            submitButton = $("#submit");

    ElementsCollection
            subjectValues = $$(".subjects-auto-complete__option");

    CalendarComponent calendarComponent = new CalendarComponent();

    public RegistrationPage openPage(){
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage formHeaderHasText(String value){
        formHeader.shouldHave(text(value));
        return this;
    }

    // Text boxes

    public RegistrationPage typeFirstName(String value){
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage typeLastName(String value){
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage typeEmail(String value){
        emailInput.setValue(value);
        return this;
    }

    public RegistrationPage typeAddress(String value){
        addressInput.setValue(value);
        return this;
    }

    // Radio button (Custom)

    public RegistrationPage selectGender(String value){
        genderRadio.$(String.format("input[value='%s']", value)).parent().click();
        return this;
    }

    public RegistrationPage typePhoneNumber(String value){
        phoneNumberInput.setValue(value);
        return this;
    }

    // DateBox

    public RegistrationPage setDateBirthDate(String year, String monthName, String day){
        birthdaySelect.click();
        calendarComponent.setDate(year, monthName, day);
        return this;
    }

    // Drop-down list with typing for selection

    public RegistrationPage selectSubject(String value){
        subjectInput.setValue(value);
        subjectValues.findBy(exactText(value)).click();
        return this;
    }

    // Drop-down

    public RegistrationPage selectState(String value){
        stateDropDown.click();
        stateDropDown.$(byText(value)).click();
        return this;
    }

    public RegistrationPage selectCity(String value){
        cityDropDown.click();
        cityDropDown.$(byText(value)).click();
        return this;
    }

    // Checkbox

    public RegistrationPage checkHobby(String value){
        hobbiesCheckBox.$(byText(value)).click();
        return this;
    }

    // Select file

    public RegistrationPage selectPicture(String path){
        uploadPictureButton.scrollTo().uploadFile(new File(path));
        return this;
    }

    public RegistrationPage selectPictureByClassPath(String classpath){
        uploadPictureButton.scrollTo().uploadFromClasspath(classpath);
        return this;
    }

    // Button

    public RegistrationPage clickSubmit(){
        submitButton.click();
        return this;
    }

}
