package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.cart.CartPage;
import pages.login.LoginPage;

public class LoginAndOpenCheckout extends BasePage {

    public LoginAndOpenCheckout(WebDriver driver,
                                WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Авторизоваться и открыть страницу корзины")
    public void loginAndOpenCheckout(String username, String password) {
        new LoginPage(driver, wait)
                .open()
                .waitForLoad()
                .typeUserName(username)
                .typePassword(password)
                .clickLoginButton();

        new CartPage(driver, wait)
                .open()
                .clickCheckout();
    }
}
