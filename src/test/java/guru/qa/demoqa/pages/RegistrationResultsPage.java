package guru.qa.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsPage {

    SelenideElement
            formHeader = $("div[role='dialog']").$(".modal-title"),
            submittedFormResult = $("div.table-responsive"),
            closeButton = $("#closeLargeModal");

    public RegistrationResultsPage formHeaderHasText(String value){
        formHeader.shouldHave(exactText(value));
        return this;
    }

    // Table

    public RegistrationResultsPage submittedFormHasRow(String key, String value){
        submittedFormResult
                .$(byText(key)).sibling(0)
                .shouldHave(exactText(value));
        return this;
    }

    // Button

    public RegistrationResultsPage clickCloseButton(){
        closeButton.click();
        return this;
    }
}
