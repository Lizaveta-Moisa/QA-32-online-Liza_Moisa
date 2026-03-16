package pages.cart;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class CartPage extends BasePage {
    public static final String TITLE_TEXT = "Your Cart";

    private static final String ENDPOINT = "cart.html";

    private static final By CART_ITEMS = By.className("cart_item");
    private static final By CHECKOUT_BUTTON = By.id("checkout");
    private static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");


    public CartPage(WebDriver driver,
                    WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Ожидание загрузки страницы")
    @Override
    public CartPage waitForLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(CHECKOUT_BUTTON));
        return this;
    }

    @Step("Открыть страницу корзины")
    public CartPage open() {
        driver.get(BASE_URL + ENDPOINT);
        return this;
    }

    @Step("Получить количество товаров в корзине")
    public int getItemsCount() {
        return driver.findElements(CART_ITEMS).size();
    }

    @Step("Нажать на кнопку Checkout")
    public void clickCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    @Step("Нажать Continue Shopping")
    public void clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }
}
