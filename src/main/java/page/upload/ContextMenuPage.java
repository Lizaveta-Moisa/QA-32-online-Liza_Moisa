package page.upload;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContextMenuPage extends BasePage{

    @FindBy(id = "hot-spot")
    private WebElement contextMenuArea;

    public ContextMenuPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @Override
    public BasePage waitForLoad() {
        wait.until(driver -> contextMenuArea.isDisplayed());
        return this;
    }

    public void openContextMenu() {
        new Actions(driver).contextClick(contextMenuArea).perform();
    }

    public String getAlertText() {
        Alert alert = wait.until(d -> d.switchTo().alert());
        return alert.getText();
    }

    public void closeAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
