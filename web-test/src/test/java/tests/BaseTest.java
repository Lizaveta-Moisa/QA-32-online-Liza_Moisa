package tests;

import common.driver.DriverManager;
import common.utils.LoggerUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    @Step("Запуск браузера перед тестом")
    public void setUp() {
        LoggerUtils.log().info("=== START TEST ===");
        driver = DriverManager.initDriver();
    }

    @AfterMethod
    @Step("Закрытие браузера после теста")
    public void tearDown() {
        LoggerUtils.log().info("=== END TEST ===");
        DriverManager.quitDriver();
    }
}
