package pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class CartPage extends BasePage {

    public static final String TITLE_TEXT = "Your Cart";

    private static final String URL = "https://www.saucedemo.com/cart.html";

    private static final By CART_ITEMS = By.className("cart_item");
    private static final By CHECKOUT_BUTTON = By.id("checkout");
    private static final By CONTINUE_SHOPPING_BUTTON = By.className("continue-shopping");

    public CartPage(WebDriver driver,
                    WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    public BasePage waitForLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(CHECKOUT_BUTTON));
        return this;
    }

    public CartPage open() {
        driver.get(URL);
        return this;
    }

    public int getItemsCount() {
        return driver.findElements(CART_ITEMS).size();
    }

    public void clickCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void removeItem(String itemName) {
        String xpath = "//div[@class='cart_item']//div[text()='" + itemName + "']/ancestor::div[@class='cart_item']//button[contains(text(),'Remove')]";
        driver.findElement(By.xpath(xpath)).click();
    }
}
