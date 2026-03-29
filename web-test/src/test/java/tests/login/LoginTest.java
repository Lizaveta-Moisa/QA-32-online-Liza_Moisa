package tests.login;

import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.AllureId;
import pages.HomePage;
import common.utils.LoggerUtils;
import steps.LoginSteps;
import tests.BaseTest;

import static common.config.Config.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginTest extends BaseTest {

    @Test
    @AllureId("TC-001")
    @Description("Проверка успешного входа с валидными данными")
    public void positiveLoginTest(){
        LoggerUtils.log().info("Старт теста: успешный логин");

        HomePage homePage = new HomePage(driver);
        homePage.openPage(BASE_URL);

        LoginSteps steps = new LoginSteps(driver);
        steps.openLoginPage();
        steps.login(VALID_EMAIL, VALID_PASSWORD);
        LoggerUtils.log().info("Успешная авторизация");

        boolean isLogoutVisible = homePage.isLogoutButtonVisible();
        LoggerUtils.log().info("Проверка видимости кнопки Logout: " + isLogoutVisible);
        assertThat(isLogoutVisible)
                .as("После успешного логина должна быть видна кнопка Logout")
                .isTrue();
    }

    @Test
    @AllureId("TC-002")
    @Description("Проверка ошибки при входе с невалидным email")
    public void invalidEmailLoginTest() {
        LoggerUtils.log().info("Старт теста: логин с невалидными данными");

        HomePage homePage = new HomePage(driver);
        homePage.openPage(BASE_URL);

        LoginSteps steps = new LoginSteps(driver);
        steps.openLoginPage();
        steps.login(INVALID_EMAIL,VALID_PASSWORD);

        String error = steps.getErrorText();

        LoggerUtils.log().info("Проверка наличия сообщения об ошибке");

        assertThat(error)
                .as("При неверных данных должно отображаться сообщение об ошибке")
                .isEqualTo("Your email or password is incorrect!");
    }

    @Test
    @AllureId("TC-004")
    @Description("Проверка ошибки при входе с невалидным паролем")
    public void invalidPasswordLoginTest() {
        LoggerUtils.log().info("Старт теста: логин с невалидными данными");

        HomePage homePage = new HomePage(driver);
        homePage.openPage(BASE_URL);

        LoginSteps steps = new LoginSteps(driver);
        steps.openLoginPage();
        steps.login(VALID_EMAIL,INVALID_PASSWORD);

        String error = steps.getErrorText();

        LoggerUtils.log().info("Проверка наличия сообщения об ошибке");

        assertThat(error)
                .as("При неверных данных должно отображаться сообщение об ошибке")
                .isEqualTo("Your email or password is incorrect!");
    }
}
