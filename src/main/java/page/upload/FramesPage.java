package page.upload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FramesPage extends BasePage{
    @FindBy(xpath = "a[href='/iframe']")
    private WebElement frame1;

    @FindBy(id = "a[href='/nested_frames']")
    private WebElement frame2;

    public FramesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @Override
    public FramesPage waitForLoad() {
        wait.until(ExpectedConditions.visibilityOf(frame1));
        wait.until(ExpectedConditions.visibilityOf(frame2));
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
