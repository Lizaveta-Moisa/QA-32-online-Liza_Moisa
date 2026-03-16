package test.cart;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.cart.CartPage;
import pages.login.LoginPage;
import pages.products.ProductsPage;
import test.BaseTest;
import utils.ScreenshotUtil;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CartTest extends BaseTest {

    private static final String USER_NAME = "standard_user";
    private static final String PASS_WORD = "secret_sauce";

    @Description("Проверка успешной загрузки корзины после авторизации")
    @Test
    public void openCartPageAndTitle() {

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.open()
                .waitForLoad()
                .typeUserName(USER_NAME)
                .typePassword(PASS_WORD)
                .clickLoginButton();

        CartPage cartPage = new CartPage(driver, wait);
        cartPage.open()
                .waitForLoad();

        ScreenshotUtil.takeScreenshot(driver);

        assertThat(cartPage.isLoaded())
                .as("Страница корзины должна успешно загрузиться после перехода")
                .isTrue();
    }

    @Description("Проверка отсутсвия товаров в корзине после авторизации")
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

        ScreenshotUtil.takeScreenshot(driver);

        assertThat(cartPage.getItemsCount())
                .as("После авторизации корзина должна быть пустой")
                .isZero();
    }

    @Description("Проверка наличия товара в корзине")
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

        new ProductsPage(driver, wait)
                .waitForLoad()
                .addProductToCart("sauce-labs-backpack")
                .clickBasketIcon();

        CartPage cartPage = new CartPage(driver, wait);
        cartPage.open()
                .waitForLoad();

        ScreenshotUtil.takeScreenshot(driver);

        assertThat(cartPage.getItemsCount())
                .as("После добавления товара 'Sauce Labs Backpack' в корзине должен быть 1 товар")
                .isEqualTo(1);
    }

    @Description("Проверка перехода на страницу оформления заказа")
    @Test
    public void checkoutButtonShouldRedirectToCheckoutPage() {
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
                .clickCheckout();

        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        ScreenshotUtil.takeScreenshot(driver);

        assertThat(driver.getCurrentUrl())
                .as("После нажатия кнопки Checkout должен произойти переход на страницу оформления заказа")
                .contains("checkout-step-one.html");
    }

    @Description("Проверка перехода на страницу товаров после нажатия на кнопку Continue Shopping")
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

        ScreenshotUtil.takeScreenshot(driver);

        assertThat(driver.getCurrentUrl())
                .as("После нажатия кнопки Continue Shopping должен произойти переход на страницу товаров")
                .contains("inventory.html");
    }
}
