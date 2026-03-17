package factory;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductsPage;

public class PageFactoryManager {
    private final WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        return new LoginPage(driver);
    }

    public ProductsPage getProductsPage() {
        return new ProductsPage(driver);
    }
}
