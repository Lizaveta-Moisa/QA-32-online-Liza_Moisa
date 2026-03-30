package pages;

import common.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends BasePage {

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    private final By nameInput = By.xpath("//input[@data-qa='signup-name']");

    private final By emailInput = By.xpath("//input[@data-qa='signup-email']");

    private final By signupButton = By.xpath("//button[@data-qa='signup-button']");

    private final By errorMessage = By.xpath("//p[contains(text(),'Email Address already exist')]");

    @Step("Вводим имя: {name}")
    public void enterName(String name) {
        WaitUtils.waitForVisible(driver, nameInput).sendKeys(name);
    }

    @Step("Вводим email: {email}")
    public void enterEmail(String email) {
        WaitUtils.waitForVisible(driver, emailInput).sendKeys(email);
    }

    @Step("Нажимаем кнопку Signup")
    public void clickSignup() {
        WaitUtils.waitForClickable(driver, signupButton).click();
    }

    @Step("Получаем текст ошибки регистрации")
    public String getErrorText() {
        return WaitUtils.waitForVisible(driver, errorMessage).getText();
    }
}
