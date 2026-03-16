package pages.checkout;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class CheckoutPage extends BasePage {
    public static final String TITLE_TEXT = "Checkout: Your Information";

    private static final String ENDPOINT = "checkout-step-one.html";

    private static final By FIRST_NAME_FIELD = By.id("first-name");
    private static final By LAST_NAME_FIELD = By.id("last-name");
    private static final By POSTAL_CODE_FIELD = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By CANCEL_BUTTON = By.id("cancel");

    public CheckoutPage(WebDriver driver,
                        WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Ожидание загрузки страницы оформлениязаказа")
    @Override
    public BasePage waitForLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON));
        return this;
    }

    @Step("Открытие страницы оформление заказа (часть 1)")
    public CheckoutPage open() {
        driver.get(BASE_URL + ENDPOINT);
        return this;
    }

    @Step("Ввести First Name")
    public CheckoutPage typeFirstName(String firstName) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        return this;
    }

    @Step("Ввести Last Name")
    public CheckoutPage typeLastName(String lastName) {
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        return this;
    }

    @Step("Ввести Postal Code")
    public CheckoutPage typePostalCode(String postalCode) {
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(postalCode);
        return this;
    }

    @Step("Нажать на кнопку Continue")
    public void clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    @Step("получение сообщения об ошибке")
    public String getErrorMessage() {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")));
        return errorElement.getText();
    }
}
