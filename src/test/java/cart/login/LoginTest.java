package cart.login;

import cart.BaseTest;
import org.junit.jupiter.api.Test;
import pages.login.LoginPage;
import pages.products.ProductsPage;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

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
                .as("Пользователь успешно вошёл и попал на страницу товаров")
                .isTrue();
    }

    // Проверка появления ошибки при входе без указания имени пользователя
    @Test
    public void testLoginWithoutUsername() {
        // ...
    }

    // Проверка появления ошибки при входе без пароля
    @Test
    public void testLoginWithoutPassword() {
        // ...
    }

    // Проверка появления ошибки при неверных учетных данных
    @Test
    public void testLoginWithInvalidCredentials() {
        // ...
    }

    // Проверка соответствия заголовка страницы
    @Test
    public void testPageTitle() {
        // ...
    }
}
