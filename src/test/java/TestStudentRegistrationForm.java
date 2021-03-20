import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static utils.RandomUtils.getRandomEmail;



public class TestStudentRegistrationForm {
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void dataAppearsInOutputBlockTest() {

        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = getRandomEmail();
        String mobileNumber = "0123456789";
        String currentAddress = faker.address().fullAddress();
        String subjects = "Computer Science";
        String gender = "Other";
        String dayOfBirth = "11";
        String monthOfBirth = "January";
        String yearOfBirth = "1990";
        String hobbies = "Music";
        String state = "Rajasthan";
        String city = "Jaipur";
        String pictureName = "avatar.png";

        // arrange

        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        // actions

        $("#firstName").setValue(firstName); //Указание значения FirstName
        $("#lastName").setValue(lastName); //Указание значения LastName
        $("#userEmail").setValue(userEmail); //Указание значения Email
        $(byText(gender)).click(); //Хотелось бы получить комментарии, как искать элемент по типу (радиобатон, чекбокс, и т.д.)
        $("#userNumber").setValue(mobileNumber); //Указание Mobile

        $("#dateOfBirthInput").click(); // Выбор месяца и года рождения в календаре
        $(".react-datepicker__month-select").click();
        $$(".react-datepicker__month-select option").find(text(monthOfBirth)).click();
        $(".react-datepicker__year-select").click();
        $$(".react-datepicker__year-select option").find(value(yearOfBirth)).click();
        $(".react-datepicker__month").click();
        $$(".react-datepicker__day").find(exactText(dayOfBirth)).click(); // Выбор дня в календаре

        $("#subjectsContainer").click(); //Указание Subject
        $("#subjectsContainer input").val(subjects).pressEnter();

        $("#uploadPicture").uploadFromClasspath(pictureName); //Указание Picture

        $("#currentAddress").setValue(currentAddress); //Указание Current Address

        $("#state").click(); //Указание State and City
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        // assert

        $x("//td[text()='Student Name']").parent().shouldHave(text(firstName + " " + lastName));
        $x("//td[text()='Student Email']").parent().shouldHave(text(userEmail));
        $x("//td[text()='Gender']").parent().shouldHave(text(gender));
        $x("//td[text()='Mobile']").parent().shouldHave(text(mobileNumber));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $x("//td[text()='Subjects']").parent().shouldHave(text(subjects));
        $x("//td[text()='Picture']").parent().shouldHave(text(pictureName));
        $x("//td[text()='Address']").parent().shouldHave(text(currentAddress));
        $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));
    }
}
