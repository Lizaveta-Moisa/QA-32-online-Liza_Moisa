package cart.login;

import cart.BaseTest;
import org.junit.jupiter.api.Test;
import pages.login.LoginPage;
import pages.products.ProductsPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pages.login.LoginPage.TITLE_TEXT;

public class LoginTest extends BaseTest {

    // Проверка успешного входа при правильных данных
    @Test
    public void LoginWithValidCredentials() {

        LoginPage loginPage = new LoginPage(driver, wait);
        ProductsPage productsPage = new ProductsPage(driver, wait);

        final String userName = "standard_user";
        final String password = "secret_sauce";

        loginPage.open()
                .waitForLoad()
                .typeUserName(userName)
                .typePassword(password)
                .clickLoginButton();

        assertThat(productsPage.isLoaded())
                .as("Пользователь не авторизовался и не попал на страницу товаров")
                .isTrue();
    }

    // Проверка появления ошибки при входе без указания имени пользователя
    @Test
    public void testLoginWithoutUsername() {

        LoginPage loginPage = new LoginPage(driver, wait);

        final String password = "secret_sauce";

        loginPage.open()
                .waitForLoad()
                .typePassword(password)
                .clickLoginButton();

        String errorMessage = loginPage.getErrorMessage();

        assertThat(errorMessage)
                .as("Проверка сообщения об ошибке при отсутствии имени пользователя")
                .isEqualTo("Epic sadface: Username is required");
    }

    // Проверка появления ошибки при входе без пароля
    @Test
    public void testLoginWithoutPassword() {

        LoginPage loginPage = new LoginPage(driver,wait);

        final String userName = "standard_user";

        loginPage.open()
                .waitForLoad()
                .typeUserName(userName)
                .clickLoginButton();

        String errorMessage = loginPage.getErrorMessage();

        assertThat(errorMessage)
                .as("Проверка сообщения об ошибке при отсутствии пароля")
                .isEqualTo("Epic sadface: Password is required");
    }

    // Проверка появления ошибки при неверных учетных данных
    @Test
    public void testLoginWithInvalidCredentials() {

        LoginPage loginPage = new LoginPage(driver, wait);

        final String userName = "UserTest";
        final String password = "PasswordTest";

        loginPage.open()
                .waitForLoad()
                .typeUserName(userName)
                .typePassword(password)
                .clickLoginButton();

        String errorMessage = loginPage.getErrorMessage();

        assertThat(errorMessage)
                .as("Проверка на неверные учетные данные")
                .isEqualTo("Epic sadface: Username and password do not match any user in this service");
    }

    // Проверка соответствия заголовка страницы
    @Test
    public void testPageTitle() {

        LoginPage  loginPage = new LoginPage(driver, wait);

        loginPage.open()
                .waitForLoad();

        String title = driver.getTitle();

        assertThat(title)
                .as("Проверка соответствия заголовка страницы")
                .isEqualTo(TITLE_TEXT);
    }
}
