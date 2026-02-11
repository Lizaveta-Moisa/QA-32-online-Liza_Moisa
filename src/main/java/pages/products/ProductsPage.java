package pages.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class ProductsPage extends BasePage {

    public static final String PAGE_TITLE = "Swag Labs";

    private static final By PAGE_HEADER = By.className("title");
    private static final By INVENTORY_ITEMS = By.cssSelector(".inventory_item");
    private static final By BASKET_ICON = By.className("shopping_cart_link");
    private static final By PRODUCT_NAME = By.cssSelector(".inventory_item_name");

    public ProductsPage(WebDriver driver,
                     WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    public ProductsPage waitForLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_HEADER));
        return this;
    }

    public boolean isLoaded() {
        // Проверяет, что заголовок страницы отображается и совпадает
        String headerText = driver.findElement(PAGE_HEADER).getText();
        return PAGE_TITLE.equals(headerText);
    }

    public int getItemsCount() {
        // Возвращает количество товаров на странице
        return driver.findElements(INVENTORY_ITEMS).size();
    }

    public String getProductNameByIndex(int index) {
        // Получает имя товара по порядковому номеру (начиная с 0)
        return driver.findElements(PRODUCT_NAME).get(index).getText();
    }

    public ProductsPage clickBasketIcon() {
        driver.findElement(BASKET_ICON).click();
        return this;
    }

    public boolean isProductInCart(String productName) {
        // Проверка наличия товара в корзине по названию
        try {
            driver.findElement(By.xpath("//div[@class='cart_item']//div[text()='" + productName + "']"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
