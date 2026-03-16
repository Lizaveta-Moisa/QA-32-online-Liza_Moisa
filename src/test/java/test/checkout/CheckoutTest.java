package test.checkout;

import org.junit.jupiter.api.Test;
import pages.cart.CartPage;
import pages.checkout.CheckoutPage;
import pages.login.LoginPage;
import test.BaseTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckoutTest extends BaseTest {
    private static final String USER_NAME = "standard_user";
    private static final String PASS_WORD = "secret_sauce";

    private static final String FIRST_NAME = "Ivan";
    private static final String LAST_NAME = "Ivanov";
    private static final String POSTAL_CODE = "12345";

    private void loginAndOpenCheckout() {
        new LoginPage(driver, wait)
                .open()
                .waitForLoad()
                .typeUserName(USER_NAME)
                .typePassword(PASS_WORD)
                .clickLoginButton();

        new CartPage(driver, wait)
                .open()
                .clickCheckout();
    }

    @Test
    public void checkoutStepOneShouldLoadSuccessfully() {
        loginAndOpenCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver, wait);
        checkoutPage.waitForLoad();

        assertThat(driver.getCurrentUrl())
                .as("После нажатия Checkout должна открыться страница checkout-step-one.html")
                .contains("checkout-step-one.html");
    }

    @Test
    public void shouldShowErrorIfFirstNameIsEmpty() {
        loginAndOpenCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver, wait)
                .typeLastName(LAST_NAME)
                .typePostalCode(POSTAL_CODE);

        checkoutPage.clickContinue();

        String error = checkoutPage.getErrorMessage();
        assertThat(error)
                .as("Должна отображаться ошибка при пустом поле First Name")
                .contains("First Name is required");
    }

    @Test
    public void shouldShowErrorIfLastNameIsEmpty() {
        loginAndOpenCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver, wait)
                .typeFirstName(FIRST_NAME)
                .typePostalCode(POSTAL_CODE);

        checkoutPage.clickContinue();

        String error = checkoutPage.getErrorMessage();
        assertThat(error)
                .as("Должна отображаться ошибка при пустом поле First Name")
                .contains("Error: Last Name is required");
    }

    @Test
    public void shouldShowErrorIfPostalCodeIsEmpty() {
        loginAndOpenCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver, wait)
                .typeFirstName(FIRST_NAME)
                .typeLastName(LAST_NAME);
        checkoutPage.clickContinue();

        String error = checkoutPage.getErrorMessage();
        assertThat(error)
                .as("Должна отображаться ошибка при пустом поле Postal Code")
                .contains("Postal Code is required");
    }

    @Test
    public void shouldRedirectToStepTwoAfterValidData() {
        loginAndOpenCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver, wait)
                .typeFirstName(FIRST_NAME)
                .typeLastName(LAST_NAME)
                .typePostalCode(POSTAL_CODE);

        checkoutPage.clickContinue();

        assertThat(driver.getCurrentUrl())
                .as("После заполнения корректных данных должен быть переход на checkout-step-two.html")
                .contains("checkout-step-two.html");
    }
}
