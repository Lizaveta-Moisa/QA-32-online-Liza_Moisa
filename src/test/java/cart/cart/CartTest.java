package cart.cart;

import cart.BaseTest;
import org.junit.jupiter.api.Test;
import pages.cart.CartPage;
import pages.login.LoginPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pages.cart.CartPage.TITLE_TEXT;

public class CartTest extends BaseTest {

    @Test
    public void testOpenCartPageAndTitle() {
        CartPage cartPage = new CartPage(driver, wait);
        cartPage.open()
                .waitForLoad();

        String pageTitle = driver.getTitle();
        assertThat(pageTitle)
                .as("Проверка заголовка страницы корзины")
                .contains(CartPage.TITLE_TEXT);
    }
}
