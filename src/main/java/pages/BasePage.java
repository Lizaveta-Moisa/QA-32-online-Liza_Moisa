package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected static final String BASE_URL = "https://www.saucedemo.com/";

    protected BasePage(WebDriver driver,
                       WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Ожидает загрузки экрана.
     *
     * @return объект экрана
     */
    public BasePage waitForLoad() {
        return null;
    }

    /**
     * Проверяет, загрузился ли экран.
     *
     * @return {@code true}, если загрузился
     */
    public boolean isLoaded() {
        try {
            waitForLoad();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
