package tests;

import factory.PageFactoryManager;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import pages.ProductsPage;
import utils.ScreenshotUtil;

public class LoginTest extends BaseTest{

    private static final String USER_NAME = "standard_user";
    private static final String PASS_WORD = "secret_sauce";

    @Test
    @Description("Успешный Login с использованием обёртки")
    public void loginWithSoftAssertions() {
        SoftAssertions soft = new SoftAssertions();

        PageFactoryManager pageManager = new PageFactoryManager(driver);

        ProductsPage productsPage = pageManager.getLoginPage()
                .open()
                .typeUserName("standard_user")
                .typePassword("secret_sauce")
                .clickLoginButton();

        ScreenshotUtil.takeScreenshot(driver);

        soft.assertThat(productsPage.isLoaded())
                .as("Пользователь не авторизовался")
                .isTrue();

        soft.assertAll();
    }
}
