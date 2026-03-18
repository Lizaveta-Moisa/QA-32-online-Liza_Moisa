package pages.login;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

public class LoginPage extends BasePage {
    public static final String TITLE_TEXT = "Swag Labs";

    private static final String URL = "https://www.saucedemo.com/";

    private static final By USERNAME_FIELD = By.id("user-name");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_LOCATOR = By.cssSelector("[data-test='error']");

    public static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver,
                     WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Ожидание загрузки страницы авторизации")
    @Override
    public LoginPage waitForLoad() {
        logger.debug("Ждём появления кнопки логина: {}", LOGIN_BUTTON);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    @Step("Открытие страницы логина")
    public LoginPage open() {
        logger.info("Открываем URL: {}", URL);
        driver.get(URL);
        return this;
    }

    @Step("Ввести имя пользователя: {userName}")
    public LoginPage typeUserName(String username) {
        logger.debug("Ввод username: {}", username);
        driver.findElement(USERNAME_FIELD).sendKeys(username);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage typePassword(String password) {
        logger.debug("Ввод пароль: {}", password);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return driver.findElement(ERROR_LOCATOR).getText();

    }

    @Step("Нажать на кнопку Login")
    public void clickLoginButton() {
        logger.debug("Клик по кнопке LOGIN: {}", LOGIN_BUTTON);
        driver.findElement(LOGIN_BUTTON).click();
    }
}
