package pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class LoginPage extends BasePage {
    public static final String TITLE_TEXT = "Swag Labs";

    private static final String URL = "https://www.saucedemo.com/";

    private static final By USERNAME_FIELD = By.id("user-name");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");

    public LoginPage(WebDriver driver,
                     WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    public LoginPage waitForLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    public LoginPage open() {
        driver.get(URL);
        return this;
    }

    public LoginPage typeUserName(String username) {
        driver.findElement(USERNAME_FIELD).sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }
}
