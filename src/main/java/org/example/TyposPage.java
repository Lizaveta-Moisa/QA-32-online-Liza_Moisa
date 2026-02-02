package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TyposPage {
    private WebDriver driver;
    private By paragraph = By.tagName("p");

    public TyposPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getParagraphText() {
        return driver.findElement(paragraph).getText();
    }
}
