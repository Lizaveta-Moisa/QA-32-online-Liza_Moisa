package pages.products;

import org.openqa.selenium.NoSuchElementException;
import pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @Override
    public ProductsPage waitForLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_HEADER));
        return this;
    }

    public boolean isLoaded() {
        String headerText = driver.findElement(PAGE_HEADER).getText();
        return PAGE_TITLE.equals(headerText);
    }

    public int getItemsCount() {
        return driver.findElements(INVENTORY_ITEMS).size();
    }

    public String getProductNameByIndex(int index) {
        return driver.findElements(PRODUCT_NAME).get(index).getText();
    }

    public ProductsPage clickBasketIcon() {
        driver.findElement(BASKET_ICON).click();
        return this;
    }

    public ProductsPage addProductToCart(String productId) {
        driver.findElement(By.id(ADD_TO_CART_BUTTON.formatted(productId))).click();
        return this;
    }

    public boolean isProductInCart(String productName) {
        try {
            driver.findElement(By.xpath("//div[@class='cart_item']//div[text()='%s']".formatted(productName)));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
