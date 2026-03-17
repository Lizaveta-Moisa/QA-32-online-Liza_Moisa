package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage{
    private final By TITLE = By.className("title");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получить заголовок страницы")
    public boolean isLoaded() {
        return driver.findElement(TITLE).isDisplayed();
    }
}
