package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps {
    private final WebDriver driver;

    private HomePage homePage;

    private LoginPage loginPage;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.homePage = new HomePage(driver);
        this.loginPage = new LoginPage(driver);
    }

    @Step("Переходим на страницу логина")
    public void openLoginPage() {
        loginPage = homePage.clickLogin();
        loginPage.verifyPageOpened();
    }

    @Step("Выполняем логин с email: {email}")
    public void login(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Step("Получаем текст ошибки логина")
    public String getErrorText() {
        return loginPage.getErrorText();
    }
}
