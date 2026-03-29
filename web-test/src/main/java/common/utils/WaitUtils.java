package common.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private static final int TIMEOUT = 10;

    private static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    @Step("Ждём, пока элемент {locator} станет видимым")
    public static WebElement waitForVisible(WebDriver driver, By locator) {
        LoggerUtils.log().info("Waiting for element to be visible: {}", locator);
        return getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Ждём, пока элемент {locator} станет кликабельным")
    public static WebElement waitForClickable(WebDriver driver, By locator) {
        LoggerUtils.log().info("Waiting for element to be clickable: {}", locator);
        return getWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }
}
