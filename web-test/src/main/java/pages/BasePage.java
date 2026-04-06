package pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import common.utils.WaitUtils;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем страницу: {url}")
    public void openPage(String url) {
        driver.get(url);
    }

    @Step("Проверяем, что страница '{name}' открыта")
    public void isPageOpened(By uniqueElement, String name) {
        WaitUtils.waitForVisible(driver, uniqueElement);
    }
}
