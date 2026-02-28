package page.upload;

import java.io.File;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadPage extends BasePage {
    private static final String TITLE = "File Uploader";
    private static final String URL = "https://the-internet.herokuapp.com/upload";

    @FindBy(tagName = "h3")
    private WebElement title;

    @FindBy(id = "file-upload")
    private WebElement fileUploader;

    @FindBy(id = "file-submit")
    private WebElement uploadButton;

    public UploadPage(WebDriver driver,
                      WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    public UploadPage waitForLoad() {
        wait.until(isTrue -> Objects.equals(title.getText(), TITLE));
        return this;
    }

    public UploadPage open() {
        driver.get(URL);
        return this;
    }

    public UploadPage upload(String filePath) {
        fileUploader.sendKeys(filePath);
        return this;
    }

    public void clickUploadButton() {
        uploadButton.click();
    }
}
