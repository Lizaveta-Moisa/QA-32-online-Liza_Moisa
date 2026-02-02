package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SortableDataTablesPage {
    private WebDriver driver;

    public SortableDataTablesPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCellText(int tableIndex, int rowIndex, int colIndex) {
        String xpath = String.format("//table[%d]//tr[%d]//td[%d]", tableIndex, rowIndex + 1, colIndex + 1);
        return driver.findElement(By.xpath(xpath)).getText();
    }
}
