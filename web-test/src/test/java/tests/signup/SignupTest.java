package tests.signup;

import assertions.HomePageAssertionsSteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.AllureId;
import pages.HomePage;
import steps.LoginSteps;
import steps.SignupSteps;
import common.utils.LoggerUtils;
import tests.BaseTest;

import static common.config.Constant.*;
import static pages.SignupPage.SIGNUP_URL;

public class SignupTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"wrong@mail.com", "123"},
                {"wrong@mail.com", "password"},
                {"user@mail.com", "545"}
        };
    }

    @Test
    @AllureId("TC-003")
    @Description("Проверка перехода на страницу регистрации")
    public void openSignupPageTest() {
        LoggerUtils.log().info("Старт теста: переход на страницу регистрации");

        HomePage homePage = new HomePage(driver);
        homePage.openPage();

        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.openLoginPage();

        SignupSteps signupSteps = new SignupSteps(driver);
        signupSteps.signup(NAME, EMAIL_SIGNUP);

        LoggerUtils.log().info("Проверка, что страница регистрации открылась");

        HomePageAssertionsSteps assertions = new HomePageAssertionsSteps();
        assertions.checkCurrentUrlContains(driver.getCurrentUrl(), SIGNUP_URL);
    }

    @Test
    @AllureId("TC-005")
    @Description("Проверка отображения ошибки при указании существующих данных пользователя на форме регистрации")
    public void signupWithEmptyDataTest() {
        LoggerUtils.log().info("Старт теста: переход на страницу регистрации");

        HomePage homePage = new HomePage(driver);
        homePage.openPage();

        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.openLoginPage();

        SignupSteps signupSteps = new SignupSteps(driver);
        signupSteps.signup(NAME, VALID_EMAIL);

        String error = signupSteps.getErrorText();

        LoggerUtils.log().info("Проверка наличия ошибки при ввода существующих данных");

        HomePageAssertionsSteps assertions = new HomePageAssertionsSteps();
        assertions.checkLoginErrorText(error, "Email Address already exist!");
    }

    @Test(dataProvider = "loginData")
    @AllureId("TC-007")
    @Description("Параметризованный тест логина с разными входными данными")
    public void parametrizedLoginTest(String email, String password) {
        HomePage homePage = new HomePage(driver);
        homePage.openPage();

        LoginSteps loginSteps = new LoginSteps(driver);

        loginSteps.openLoginPage();
        loginSteps.login(email, password);

        String error = loginSteps.getErrorText();

        LoggerUtils.log().info("Проверка наличия ошибки при пустых данных");

        HomePageAssertionsSteps assertions = new HomePageAssertionsSteps();
        assertions.checkLoginErrorText(error, "Your email or password is incorrect!");
    }
}
