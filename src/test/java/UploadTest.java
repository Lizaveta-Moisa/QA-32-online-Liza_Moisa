import org.testng.annotations.Test;
import page.upload.UploadPage;
import page.upload.UploadSuccessPage;

import static org.assertj.core.api.Assertions.assertThat;

public class UploadTest extends BaseTest{

    @Test
    public void fileUpload() {
        final String fileName = "base_schema.xml";
        final String filePath =
                "/Users/danborisevich/Downloads/УЧЕБА/practice-makes-perfect/src/main/resources/files/%s".formatted(fileName);
        new UploadPage(driver, wait)
                .open()
                .waitForLoad()
                .upload(filePath)
                .clickUploadButton();
        UploadSuccessPage uploadSuccessPage = new UploadSuccessPage(driver, wait);
        uploadSuccessPage
                .waitForLoad();
        String actualFileName = uploadSuccessPage.getFileName();
        assertThat(actualFileName)
                .as("Название загруженного файла '%s' не совпадает '%s'".formatted(actualFileName, filePath))
                .isEqualTo(fileName);
    }
}
