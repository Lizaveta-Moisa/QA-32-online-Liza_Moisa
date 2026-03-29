package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.SignupPage;

public class SignupSteps {
    private final WebDriver driver;
    private HomePage homePage;
    private SignupPage signupPage;

    public SignupSteps(WebDriver driver) {
        this.driver = driver;
        this.signupPage = new SignupPage(driver);
    }

    @Step("Выполняем регистрацию пользователя")
    public void signup(String name, String email) {
        signupPage.enterName(name);
        signupPage.enterEmail(email);
        signupPage.clickSignup();
    }

    @Step("Получаем текст ошибки регистрации")
    public String getErrorText() {
        return signupPage.getErrorText();
    }
}
