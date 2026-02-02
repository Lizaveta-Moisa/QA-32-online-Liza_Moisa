package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HoversPage {
    private WebDriver driver;
    private Actions actions;

    public HoversPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void hoverOverProfile(int index) {
        String locator = "(//div[@class='figure'])[" + index + "]";
        WebElement element = driver.findElement(By.xpath(locator));
        actions.moveToElement(element).perform();
    }

    public String getProfileName(int index) {
        String nameLocator = "(//div[@class='figcaption'])[" + index + "]";
        return driver.findElement(By.xpath(nameLocator)).getText();
    }

    public void clickProfileLink(int index) {
        String linkLocator = "(//div[@class='figure'])[" + index + "]//a";
        driver.findElement(By.xpath(linkLocator)).click();
    }
}
