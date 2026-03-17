package pages;

import elements.Button;
import elements.InputField;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage  extends BasePage{
    private static final String URL = "https://www.saucedemo.com/";

    private final By USERNAME_FIELD = By.id("user-name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы логина")
    public LoginPage open() {
        driver.get(URL);
        return this;
    }

    @Step("Ввести имя пользователя: {userName}")
    public LoginPage typeUserName(String username) {
        new InputField(driver.findElement(USERNAME_FIELD)).type(username);
        return this;
    }

    @Step("Ввести пароль: {passWorld}")
    public LoginPage typePassword(String password) {
        new InputField(driver.findElement(PASSWORD_FIELD)).type(password);
        return this;
    }

    @Step("Нажать на кнопку Login")
    public ProductsPage clickLoginButton() {
        new Button(driver.findElement(LOGIN_BUTTON)).click();
        return new ProductsPage(driver);
    }
}
