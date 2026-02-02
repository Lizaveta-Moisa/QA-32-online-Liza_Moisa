package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxesPage {
    private WebDriver driver;
    private By checkboxes = By.cssSelector("input[type='checkbox']");

    public CheckboxesPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getCheckbox(int index) {
        return driver.findElements(checkboxes).get(index);
    }

    public boolean isChecked(int index) {
        return getCheckbox(index).isSelected();
    }

    public void setCheckbox(int index, boolean check) {
        WebElement checkbox = getCheckbox(index);
        if (checkbox.isSelected() != check) {
            checkbox.click();
        }
    }
}
