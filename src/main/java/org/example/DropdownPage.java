package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage {
    private WebDriver driver;
    private By dropdownLocator = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectOption(int index) {
        Select select = new Select(driver.findElement(dropdownLocator));
        select.selectByIndex(index);
    }

    public String getSelectedOptionText() {
        Select select = new Select(driver.findElement(dropdownLocator));
        return select.getFirstSelectedOption().getText();
    }
}
