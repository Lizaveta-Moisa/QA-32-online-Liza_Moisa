package pages;

import org.openqa.selenium.By;
import io.qameta.allure.Step;
import common.utils.WaitUtils;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final String BASE_URL = "https://automationexercise.com";

    private final By loginButton = By.xpath("//a[@href='/login']");

    private final By logoutButton = By.xpath("//a[normalize-space()='Logout']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        super.openPage(BASE_URL); // вызываем универсальный метод BasePage
    }

    @Step("Нажимаем кнопку Login")
    public LoginPage clickLogin() {
        WaitUtils.waitForClickable(driver, loginButton).click();
        return new LoginPage(driver);
    }

    @Step("Нажимаем кнопку Logout")
    public void clickLogout() {
        WaitUtils.waitForClickable(driver, logoutButton).click();
    }

    @Step("Проверяем видимость кнопки Logout")
    public boolean isLogoutButtonVisible() {
        return !driver.findElements(logoutButton).isEmpty() &&
                driver.findElement(logoutButton).isDisplayed();
    }

    @Step("Проверяем видимость кнопки Login")
    public boolean isLoginButtonVisible() {
        return !driver.findElements(loginButton).isEmpty() &&
                driver.findElement(loginButton).isDisplayed();
    }
}
