package tests;

import factory.PageFactoryManager;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ScreenshotUtil;

public class LoginTest extends BaseTest{

    private static final String USER_NAME = "standard_user";
    private static final String PASS_WORD = "secret_sauce";

    @Test
    @Description("Login через обычные обёрки")
    public void loginWithSoftAssertions() {
        SoftAssertions soft = new SoftAssertions();

        PageFactoryManager pageManager = new PageFactoryManager(driver);

        LoginPage loginPage = pageManager.getLoginPage();
        ProductsPage productsPage = pageManager.getProductsPage();

        loginPage.open()
                .typeUserName(USER_NAME)
                .typePassword(PASS_WORD)
                .clickLoginButton();

        ScreenshotUtil.takeScreenshot(driver);

        soft.assertThat(productsPage.isLoaded())
                .as("Пользователь не авторизовался")
                .isTrue();

        soft.assertAll();
    }
}
