package guru.qa.demoqa.pages.components;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {

    public void setDate(String year, String monthName, String day){
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(monthName);
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .find(exactText(day)).click();
    }

}
