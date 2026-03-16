package pages.products;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class ProductsPage extends BasePage {
    public static final String PAGE_TITLE = "Products";

    private static final By PAGE_HEADER = By.className("title");
    private static final By INVENTORY_ITEMS = By.cssSelector(".inventory_item");
    private static final By BASKET_ICON = By.className("shopping_cart_link");
    private static final By PRODUCT_NAME = By.cssSelector(".inventory_item_name");
    private static final String ADD_TO_CART_BUTTON = "add-to-cart-%s";

    public ProductsPage(WebDriver driver,
                        WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Ожидание загрузки страницы с товарами")
    @Override
    public ProductsPage waitForLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_HEADER));
        return this;
    }

    @Step("Получить заголовок страницы")
    public boolean isLoaded() {
        String headerText = driver.findElement(PAGE_HEADER).getText();
        return PAGE_TITLE.equals(headerText);
    }

    @Step("Получить количество товаров")
    public int getItemsCount() {
        return driver.findElements(INVENTORY_ITEMS).size();
    }

    @Step("Получить название товара по индексу {index}")
    public String getProductNameByIndex(int index) {
        return driver.findElements(PRODUCT_NAME).get(index).getText();
    }

    @Step("Нажать на иконку корзины")
    public ProductsPage clickBasketIcon() {
        driver.findElement(BASKET_ICON).click();
        return this;
    }

    @Step("Добавить товар в корзину: {productId}")
    public ProductsPage addProductToCart(String productId) {
        driver.findElement(By.id(ADD_TO_CART_BUTTON.formatted(productId))).click();
        return this;
    }

    @Step("Получить товар в корзине по названию")
    public boolean isProductInCart(String productName) {
        try {
            driver.findElement(By.xpath("//div[@class='cart_item']//div[text()='%s']".formatted(productName)));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
