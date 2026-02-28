package page.upload;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicControlsPage extends BasePage{
    @FindBy(id = "checkbox")
    private WebElement checkbox;

    @FindBy(xpath = "//button[text()='Remove']")
    private WebElement removeButton;

    @FindBy(xpath = "//button[text()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//button[text()='Enable']")
    private WebElement addInput;

    @FindBy(id = "message")
    private WebElement message;

    @FindBy(css = "input[type='text']")
    private WebElement input;

    public DynamicControlsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @Override
    public BasePage waitForLoad() {
        wait.until(driver -> removeButton.isDisplayed());
        return this;
    }

    public void clickRemove() {
        removeButton.click();
    }

    public void clickAdd() {
        addButton.click();
    }

    public void clickInput() {
        addInput.click();
    }

    public boolean isCheckboxDisplayed() {
        try {
            return checkbox.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String waitForMessage(String expectedMessage) {
        wait.until(ExpectedConditions.textToBePresentInElement(message, expectedMessage));
        return message.getText();
    }

    public WebElement getInput() {
        return input;
    }
}
