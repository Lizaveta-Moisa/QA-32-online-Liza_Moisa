package test.login;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.login.LoginPage;
import pages.products.ProductsPage;
import utils.ScreenshotUtil;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pages.login.LoginPage.TITLE_TEXT;

public class LoginTest extends test.BaseTest {

    private static final String USER_NAME = "standard_user";
    private static final String PASS_WORD = "secret_sauce";

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Description("Проверка авторизации с валидными данными")
    @Test
    public void loginWithValidCredentials() {
        logger.info("=== START TEST: loginWithValidCredentials ===");

        LoginPage loginPage = new LoginPage(driver, wait);
        ProductsPage productsPage = new ProductsPage(driver, wait);

        logger.info("Авторизуем на странице с валидными логин и паролем");
        loginPage.open()
                .waitForLoad()
                .typeUserName(USER_NAME)
                .typePassword(PASS_WORD)
                .clickLoginButton();

        logger.info("Делаем скриншот");
        ScreenshotUtil.takeScreenshot(driver);

        logger.info("Проверка загрузки страницы продуктов");
        assertThat(productsPage.isLoaded())
                .as("Пользователь не авторизовался и не попал на страницу товаров")
                .isTrue();

        logger.info("=== END TEST: SUCCESS ===");
    }

    @Description("Проверка сообщения об ошибке при отсутствии имени пользователя")
    @Test
    public void loginWithoutUsername() {
        logger.info("=== START TEST: loginWithoutUsername ===");
        LoginPage loginPage = new LoginPage(driver, wait);

        logger.info("Открываем страницу логина");
        loginPage.open()
                .waitForLoad();

        logger.info("Вводим только пароль и нажимаем кнопку логина");
        loginPage.typePassword(PASS_WORD)
                .clickLoginButton();

        logger.info("Делаем скриншот");
        ScreenshotUtil.takeScreenshot(driver);

        logger.info("Получаем текст ошибки");
        String errorMessage = loginPage.getErrorMessage();

        logger.info("Проверяем текст ошибки");
        assertThat(errorMessage)
                .as("Ошибка отсутствует или некорректная")
                .isEqualTo("Epic sadface: Username is required");

        logger.info("=== END TEST: SUCCESS ===");
    }

    @Description("Проверка сообщения об ошибке при отсутствии пароля")
    @Test
    public void loginWithoutPassword() {
        logger.info("=== START TEST: loginWithoutPassword ===");
        LoginPage loginPage = new LoginPage(driver, wait);

        logger.info("Открываем страницу логина");
        loginPage.open()
                .waitForLoad();

        logger.info("Вводим только имя пользователя и нажимаем кнопку логина");
        loginPage.typeUserName(USER_NAME)
                .clickLoginButton();

        logger.info("Делаем скриншот");
        ScreenshotUtil.takeScreenshot(driver);

        logger.info("Получаем текст ошибки");
        String errorMessage = loginPage.getErrorMessage();

        logger.info("Проверяем текст ошибки");
        assertThat(errorMessage)
                .as("Ошибка отсутствует или некорректна")
                .isEqualTo("Epic sadface: Password is required");

        logger.info("=== END TEST: SUCCESS ===");
    }

    @Description("Проверка сообщения об ошибке при неверных учетных данных")
    @Test
    public void loginWithInvalidCredentials() {
        logger.info("=== START TEST: loginWithInvalidCredentials ===");
        LoginPage loginPage = new LoginPage(driver, wait);

        final String userName = "UserTest";
        final String password = "PasswordTest";

        logger.info("Открываем страницу логина");
        loginPage.open()
                .waitForLoad();

        logger.info("Вводим невалидные логин и пароль");
        loginPage.typeUserName(userName)
                .typePassword(password)
                .clickLoginButton();

        logger.info("Делаем скриншот");
        ScreenshotUtil.takeScreenshot(driver);

        logger.info("Получаем текст ошибки");
        String errorMessage = loginPage.getErrorMessage();

        logger.info("Проверяем текст ошибки");
        assertThat(errorMessage)
                .as("Ошибка отсутствует или некорректна")
                .isEqualTo("Epic sadface: Username and password do not match any user in this service");

        logger.info("=== END TEST: SUCCESS ===");
    }

    @Description("Проверка соответствия заголовка страницы")
    @Test
    public void pageTitle() {
        logger.info("=== START TEST: pageTitle ===");

        LoginPage loginPage = new LoginPage(driver, wait);

        logger.info("Открываем страницу логина");
        loginPage.open()
                .waitForLoad();

        logger.info("Делаем скриншот");
        ScreenshotUtil.takeScreenshot(driver);

        logger.info("Получаем заголовок страницы");
        String title = driver.getTitle();

        logger.info("Проверяем заголовок страницы");
        assertThat(title)
                .as("Заголовок не соответствует заголовку страницы")
                .isEqualTo(TITLE_TEXT);

        logger.info("=== END TEST: SUCCESS ===");
    }
}
