package pages;

import elements.Button;
import elements.InputField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By USERNAME_FIELD = By.id("user-name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage open() {
        driver.get("https://www.saucedemo.com/");
        return this;
    }

    public LoginPage typeUserName(String username) {
        new InputField(driver.findElement(USERNAME_FIELD)).type(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        new InputField(driver.findElement(PASSWORD_FIELD)).type(password);
        return this;
    }

    public void clickLoginButton() {
        new Button(driver.findElement(LOGIN_BUTTON)).click();
    }
}
