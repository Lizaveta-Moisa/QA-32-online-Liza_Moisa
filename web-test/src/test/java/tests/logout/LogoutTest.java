package tests.logout;

import common.utils.LoggerUtils;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.HomePage;
import steps.LoginSteps;
import tests.BaseTest;

import static common.config.Config.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LogoutTest extends BaseTest {
    @Test
    @AllureId("TC-006")
    @Description("Проверка успешного логаута")
    public void successfulLogoutTest() {
        LoggerUtils.log().info("Старт теста: успешный логин и выход");

        HomePage homePage = new HomePage(driver);
        homePage.openPage(BASE_URL);

        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.openLoginPage();
        loginSteps.login(VALID_EMAIL, VALID_PASSWORD);
        LoggerUtils.log().info("Успешная авторизация");

        homePage.clickLogout();
        LoggerUtils.log().info("Нажата кнопка Logout");

        boolean isLoginVisible = homePage.isLoginButtonVisible();
        LoggerUtils.log().info("Проверка наличия кнопки Login после логаута: " + isLoginVisible);

        assertThat(isLoginVisible)
                .as("После успешного логаута должна отображаться кнопка Login")
                .isTrue();
    }
}
