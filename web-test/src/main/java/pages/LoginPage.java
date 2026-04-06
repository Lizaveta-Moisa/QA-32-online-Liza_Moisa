package pages;

import common.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By emailInput = By.xpath("//input[@data-qa='login-email']");

    private final By passwordInput = By.xpath("//input[@data-qa='login-password']");

    private final By loginButton = By.xpath("//button[@data-qa='login-button']");

    private final By errorMessage = By.xpath("//p[contains(text(),'incorrect')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Вводим email: {email}")
    public void enterEmail(String email) {
        WaitUtils.waitForVisible(driver, emailInput).sendKeys(email);
    }

    @Step("Вводим пароль")
    public void enterPassword(String password) {
        WaitUtils.waitForVisible(driver, passwordInput).sendKeys(password);
    }

    @Step("Нажимаем кнопку Login")
    public void clickLogin() {
        WaitUtils.waitForClickable(driver, loginButton).click();
    }

    @Step("Получаем текст ошибки")
    public String getErrorText() {
        return WaitUtils.waitForVisible(driver, errorMessage).getText();
    }

    @Step("Проверяем, что LoginPage открыта")
    public void verifyPageOpened() {
        isPageOpened(emailInput, "LoginPage");
    }
}
