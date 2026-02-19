package page.upload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FramesPage extends BasePage{
    @FindBy(id = "frame1")
    private WebElement frame1;

    @FindBy(id = "frame2")
    private WebElement frame2;

    public FramesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @Override
    public BasePage waitForLoad() {
        wait.until(driver -> frame1.isDisplayed() && frame2.isDisplayed());
        return this;
    }
    public void switchToFrame1() {
        driver.switchTo().frame(frame1);
    }

    public void switchToFrame2() {
        driver.switchTo().frame(frame2);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public String getParagraphTextInFrame1() {
        return driver.findElement(By.tagName("p")).getText();
    }
}
