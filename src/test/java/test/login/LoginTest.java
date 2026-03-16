package test.login;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import pages.login.LoginPage;
import pages.products.ProductsPage;
import utils.ScreenshotUtil;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pages.login.LoginPage.TITLE_TEXT;

public class LoginTest extends test.BaseTest {

    private static final String USER_NAME = "standard_user";
    private static final String PASS_WORD = "secret_sauce";

    @Description("Проверка авторизации с валидными данными")
    @Test
    public void loginWithValidCredentials() {

        LoginPage loginPage = new LoginPage(driver, wait);
        ProductsPage productsPage = new ProductsPage(driver, wait);

        loginPage.open()
                .waitForLoad()
                .typeUserName(USER_NAME)
                .typePassword(PASS_WORD)
                .clickLoginButton();

        ScreenshotUtil.takeScreenshot(driver);

        assertThat(productsPage.isLoaded())
                .as("Пользователь не авторизовался и не попал на страницу товаров")
                .isTrue();
    }

    @Description("Проверка сообщения об ошибке при отсутствии имени пользователя")
    @Test
    public void loginWithoutUsername() {

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.open()
                .waitForLoad()
                .typePassword(PASS_WORD)
                .clickLoginButton();

        ScreenshotUtil.takeScreenshot(driver);

        String errorMessage = loginPage.getErrorMessage();

        assertThat(errorMessage)
                .as("Ошибка отсутствует или некорректная")
                .isEqualTo("Epic sadface: Username is required");
    }

    @Description("Проверка сообщения об ошибке при отсутствии пароля")
    @Test
    public void loginWithoutPassword() {

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.open()
                .waitForLoad()
                .typeUserName(USER_NAME)
                .clickLoginButton();

        ScreenshotUtil.takeScreenshot(driver);

        String errorMessage = loginPage.getErrorMessage();

        assertThat(errorMessage)
                .as("Ошибка отсутствует или некорректна")
                .isEqualTo("Epic sadface: Password is required");
    }

    @Description("Проверка сообщения об ошибке при неверных учетных данных")
    @Test
    public void loginWithInvalidCredentials() {

        LoginPage loginPage = new LoginPage(driver, wait);

        final String userName = "UserTest";
        final String password = "PasswordTest";

        loginPage.open()
                .waitForLoad()
                .typeUserName(userName)
                .typePassword(password)
                .clickLoginButton();

        ScreenshotUtil.takeScreenshot(driver);

        String errorMessage = loginPage.getErrorMessage();

        assertThat(errorMessage)
                .as("Ошибка отсутствует или некорректна")
                .isEqualTo("Epic sadface: Username and password do not match any user in this service");
    }

    @Description("Проверка соответствия заголовка страницы")
    @Test
    public void pageTitle() {

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.open()
                .waitForLoad();

        ScreenshotUtil.takeScreenshot(driver);

        String title = driver.getTitle();

        assertThat(title)
                .as("Заголовок не соответствует заголовку страницы")
                .isEqualTo(TITLE_TEXT);
    }
}
