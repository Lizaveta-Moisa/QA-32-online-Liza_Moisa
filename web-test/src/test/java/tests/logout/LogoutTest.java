package tests.logout;

import assertions.HomePageAssertionsSteps;
import common.utils.LoggerUtils;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.HomePage;
import steps.LoginSteps;
import tests.BaseTest;

import static common.config.Constant.*;

public class LogoutTest extends BaseTest {
    @Test
    @AllureId("TC-006")
    @Description("Проверка успешного логаута")
    public void successfulLogoutTest() {
        LoggerUtils.log().info("Старт теста: успешный логин и выход");

        HomePage homePage = new HomePage(driver);
        homePage.openPage();

        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.openLoginPage();
        loginSteps.login(VALID_EMAIL, VALID_PASSWORD);
        LoggerUtils.log().info("Успешная авторизация");

        homePage.clickLogout();
        LoggerUtils.log().info("Нажата кнопка Logout");

        HomePageAssertionsSteps assertions = new HomePageAssertionsSteps();
        assertions.checkLoginButtonVisible(homePage.isLoginButtonVisible());
    }
}
