package cart.checkout;

import cart.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.cart.CartPage;
import pages.checkout.CheckoutPage;
import pages.login.LoginPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckoutTest extends BaseTest {
    private static final String userName = "standard_user";
    private static final String passWord = "secret_sauce";

    private static final String firstName = "Ivan";
    private static final String lastName = "Ivanov";
    private static final String postalCode = "12345";

    private void loginAndOpenCheckout() {
        new LoginPage(driver, wait)
                .open()
                .waitForLoad()
                .typeUserName(userName)
                .typePassword(passWord)
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
                .typeLastName(lastName)
                .typePostalCode(postalCode);

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
                .typeFirstName(firstName)
                .typePostalCode(postalCode);

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
                .typeFirstName(firstName)
                .typeLastName(lastName);
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
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typePostalCode(postalCode);

        checkoutPage.clickContinue();

        assertThat(driver.getCurrentUrl())
                .as("После заполнения корректных данных должен быть переход на checkout-step-two.html")
                .contains("checkout-step-two.html");
    }
}
