package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddRemoveElementsPage {
    private WebDriver driver;

    private By addButton = By.xpath("//button[text()='Add Element']");
    private By deleteButton = By.xpath("//button[text()='Delete']");

    public AddRemoveElementsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addElement(){
        driver.findElement(addButton).click();
    }

    public void removeElement(){
        driver.findElement(deleteButton).click();
    }

    public int getElementsCount(){
        return driver.findElements(deleteButton).size();
    }
}
