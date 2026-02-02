package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputsPage {
    private WebDriver driver;
    private By input = By.tagName("input");

    public InputsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterValue(String value) {
        WebElement inputElement = driver.findElement(input);
        inputElement.clear();
        inputElement.sendKeys(value);
    }
}
