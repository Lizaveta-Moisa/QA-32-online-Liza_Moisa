package test.checkout;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import pages.checkout.CheckoutPage;
import steps.LoginAndOpenCheckout;
import test.BaseTest;
import utils.ScreenshotUtil;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckoutTest extends BaseTest {
    private static final String USER_NAME = "standard_user";
    private static final String PASS_WORD = "secret_sauce";

    private static final String FIRST_NAME = "Ivan";
    private static final String LAST_NAME = "Ivanov";
    private static final String POSTAL_CODE = "12345";

    @Description("Проверка открытия страницы checkout-step-one.html")
    @Test
    public void checkoutStepOneShouldLoadSuccessfully() {
        LoginAndOpenCheckout loginAndOpenCheckout = new LoginAndOpenCheckout(driver, wait);
        loginAndOpenCheckout.loginAndOpenCheckout(USER_NAME,PASS_WORD);

        CheckoutPage checkoutPage = new CheckoutPage(driver, wait);
        checkoutPage.waitForLoad();

        ScreenshotUtil.takeScreenshot(driver);

        assertThat(driver.getCurrentUrl())
                .as("После нажатия Checkout должна открыться страница checkout-step-one.html")
                .contains("checkout-step-one.html");
    }

    @Description("Проверка наличия ошибки при не заполненном поле First Name")
    @Test
    public void shouldShowErrorIfFirstNameIsEmpty() {
        LoginAndOpenCheckout loginAndOpenCheckout = new LoginAndOpenCheckout(driver, wait);
        loginAndOpenCheckout.loginAndOpenCheckout(USER_NAME,PASS_WORD);

        CheckoutPage checkoutPage = new CheckoutPage(driver, wait)
                .typeLastName(LAST_NAME)
                .typePostalCode(POSTAL_CODE);

        checkoutPage.clickContinue();

        ScreenshotUtil.takeScreenshot(driver);

        String error = checkoutPage.getErrorMessage();
        assertThat(error)
                .as("Должна отображаться ошибка при пустом поле First Name")
                .contains("First Name is required");
    }

    @Description("Проверка наличия ошибки при не заполненном поле Last Name")
    @Test
    public void shouldShowErrorIfLastNameIsEmpty() {
        LoginAndOpenCheckout loginAndOpenCheckout = new LoginAndOpenCheckout(driver, wait);
        loginAndOpenCheckout.loginAndOpenCheckout(USER_NAME,PASS_WORD);

        CheckoutPage checkoutPage = new CheckoutPage(driver, wait)
                .typeFirstName(FIRST_NAME)
                .typePostalCode(POSTAL_CODE);

        checkoutPage.clickContinue();

        ScreenshotUtil.takeScreenshot(driver);

        String error = checkoutPage.getErrorMessage();
        assertThat(error)
                .as("Должна отображаться ошибка при пустом поле Last Name")
                .contains("Error: Last Name is required");
    }

    @Description("Проверка наличия ошибки при не заполненном поле Postal Code")
    @Test
    public void shouldShowErrorIfPostalCodeIsEmpty() {
        LoginAndOpenCheckout loginAndOpenCheckout = new LoginAndOpenCheckout(driver, wait);
        loginAndOpenCheckout.loginAndOpenCheckout(USER_NAME,PASS_WORD);

        CheckoutPage checkoutPage = new CheckoutPage(driver, wait)
                .typeFirstName(FIRST_NAME)
                .typeLastName(LAST_NAME);
        checkoutPage.clickContinue();

        ScreenshotUtil.takeScreenshot(driver);

        String error = checkoutPage.getErrorMessage();
        assertThat(error)
                .as("Должна отображаться ошибка при пустом поле Postal Code")
                .contains("Postal Code is required");
    }

    @Description("Проверка перехода на второй шаг оформления заказа (checkout-step-two.html)")
    @Test
    public void shouldRedirectToStepTwoAfterValidData() {
        LoginAndOpenCheckout loginAndOpenCheckout = new LoginAndOpenCheckout(driver, wait);
        loginAndOpenCheckout.loginAndOpenCheckout(USER_NAME,PASS_WORD);

        CheckoutPage checkoutPage = new CheckoutPage(driver, wait)
                .typeFirstName(FIRST_NAME)
                .typeLastName(LAST_NAME)
                .typePostalCode(POSTAL_CODE);

        checkoutPage.clickContinue();

        ScreenshotUtil.takeScreenshot(driver);

        assertThat(driver.getCurrentUrl())
                .as("После заполнения корректных данных должен быть переход на checkout-step-two.html")
                .contains("checkout-step-two.html");
    }
}
