package page.upload;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUploadPage extends BasePage{
    @FindBy(id = "file-upload")
    private WebElement uploadInput;

    @FindBy(id = "file-submit")
    private WebElement uploadButton;

    @FindBy(id = "uploaded-files")
    private WebElement uploadedFilesText;

    public FileUploadPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @Override
    public BasePage waitForLoad() {
        wait.until(driver -> uploadInput.isDisplayed());
        return this;
    }
    public void uploadFile(String filePath) {
        uploadInput.sendKeys(filePath);
        uploadButton.click();
    }

    public String getUploadedFileName() {
        return uploadedFilesText.getText();
    }
}
