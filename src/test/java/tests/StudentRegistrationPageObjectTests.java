package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationPage;

public class StudentRegistrationPageObjectTests extends TestBase {
    StudentRegistrationPage studentRegistrationPage;

    @Test
    @DisplayName("Успешное заполнение формы регистрации студента")
    void successfulFillFormTest() {
        studentRegistrationPage = new StudentRegistrationPage();

        studentRegistrationPage.openPage();
        studentRegistrationPage.fillForm();
        studentRegistrationPage.checkData();
    }
}