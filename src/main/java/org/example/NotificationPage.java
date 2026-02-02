package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NotificationPage {
    private WebDriver driver;
    private By button = By.xpath("//p[@id='notification']/following-sibling::button");
    private By notification = By.id("flash");

    public NotificationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButton() {
        driver.findElement(button).click();
    }

    public String getNotificationText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(notification));
        return driver.findElement(notification).getText();
    }
}
