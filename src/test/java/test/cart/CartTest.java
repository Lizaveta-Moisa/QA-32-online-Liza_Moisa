package test.cart;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.cart.CartPage;
import pages.login.LoginPage;
import pages.products.ProductsPage;
import test.BaseTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CartTest extends BaseTest {
    private static final String USER_NAME = "standard_user";
    private static final String PASS_WORD = "secret_sauce";

    @Test
    public void testOpenCartPageAndTitle() {

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.open()
                .waitForLoad()
                .typeUserName(USER_NAME)
                .typePassword(PASS_WORD)
                .clickLoginButton();

        CartPage cartPage = new CartPage(driver, wait);
        cartPage.open()
                .waitForLoad();

        assertThat(cartPage.isLoaded())
                .as("Страница корзины должна успешно загрузиться после перехода")
                .isTrue();
    }

    @Test
    public void cartShouldBeEmptyAfterLogin() {

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.open()
                .waitForLoad()
                .typeUserName(USER_NAME)
                .typePassword(PASS_WORD)
                .clickLoginButton();

        CartPage cartPage = new CartPage(driver, wait);
        cartPage.open();

        assertThat(cartPage.getItemsCount())
                .as("После авторизации корзина должна быть пустой")
                .isZero();
    }

    @Test
    public void cartShouldContainOneItemAfterAddingProduct() {
        new LoginPage(driver, wait)
                .open()
                .waitForLoad()
                .typeUserName(USER_NAME)
                .typePassword(PASS_WORD)
                .clickLoginButton();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        CartPage cartPage = new CartPage(driver, wait);
        cartPage.open()
                .waitForLoad();

        assertThat(cartPage.getItemsCount())
                .as("После добавления товара 'Sauce Labs Backpack' в корзине должен быть 1 товар")
                .isEqualTo(1);
    }

    @Test
    public void checkoutButtonShouldRedirectToCheckoutPage() {
        new LoginPage(driver, wait)
                .open()
                .waitForLoad()
                .typeUserName(USER_NAME)
                .typePassword(PASS_WORD)
                .clickLoginButton();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        CartPage cartPage = new CartPage(driver, wait);
        cartPage.open()
                .waitForLoad();

        driver.findElement(By.id("checkout")).click();

        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        assertThat(driver.getCurrentUrl())
                .as("После нажатия кнопки Checkout должен произойти переход на страницу оформления заказа")
                .contains("checkout-step-one.html");
    }

    @Test
    public void continueShoppingShouldRedirectToInventoryPage() {
        new LoginPage(driver, wait)
                .open()
                .waitForLoad()
                .typeUserName(USER_NAME)
                .typePassword(PASS_WORD)
                .clickLoginButton();

        new ProductsPage(driver, wait)
                .waitForLoad()
                .addProductToCart("sauce-labs-backpack")
                .clickBasketIcon();

        CartPage cartPage = new CartPage(driver, wait);
        cartPage.open()
                .waitForLoad()
                .clickContinueShopping();

        assertThat(driver.getCurrentUrl())
                .as("После нажатия кнопки Continue Shopping должен произойти переход на страницу товаров")
                .contains("inventory.html");
    }
}
