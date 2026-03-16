package tests;

import factory.PageFactoryManager;
import driver.DriverSingleton;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class LoginTest {

    @Attachment(value = "Скриншот страницы", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) DriverSingleton.getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }

    @Test
    @Description("Login через обычные обёрки")
    public void loginWithSoftAssertions() {
        SoftAssertions soft = new SoftAssertions();

        PageFactoryManager pageManager = new PageFactoryManager(DriverSingleton.getDriver());
        LoginPage loginPage = pageManager.getLoginPage();
        ProductsPage productsPage = pageManager.getProductsPage();

        loginPage.open()
                .typeUserName("standard_user")
                .typePassword("secret_sauce")
                .clickLoginButton();

        takeScreenshot();

        soft.assertThat(productsPage.isLoaded())
                .as("Пользователь не авторизовался")
                .isTrue();

        soft.assertAll();
        DriverSingleton.closeDriver();
    }
}
