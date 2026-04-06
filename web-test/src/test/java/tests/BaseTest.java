package tests;

import common.driver.DriverManager;
import common.utils.LoggerUtils;
import io.qameta.allure.Step;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    @Step("Запуск браузера перед тестом")
    public void setUp() {
        LoggerUtils.log().info("=== START TEST ===");
        driver = DriverManager.getDriver();
    }

    @AfterMethod
    @Step("Закрытие браузера после теста")
    public void tearDown() {
        LoggerUtils.log().info("=== END TEST ===");
        DriverManager.quitDriver();
    }
}
