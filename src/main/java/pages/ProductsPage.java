package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    private final WebDriver driver;
    private final By TITLE = By.className("title");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        return driver.findElement(TITLE).isDisplayed();
    }
}
