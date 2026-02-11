import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DemoTest {
    WebDriver driver;

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void  teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testAddProductAndVerify() {
        driver.get("https://www.saucedemo.com");

        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//button")).click();

        driver.findElement(By.cssSelector("a.shopping_cart_link")).click();

        String name = driver.findElement(By.xpath("//div[@class='cart_item']//div[@class='inventory_item_name']")).getText();
        String price = driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();

        Assertions.assertThat(name).isEqualTo("Sauce Labs Backpack").as("Текст заголовка не соответствует");
        Assertions.assertThat(price).isEqualTo("$29.99").as("Цена не соответсвует");
    }
}
