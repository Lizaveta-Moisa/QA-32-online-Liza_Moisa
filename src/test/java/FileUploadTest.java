import org.testng.annotations.Test;
import page.upload.FileUploadPage;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.AssertJUnit.fail;

public class FileUploadTest extends BaseTest {
    private final String baseUrl = "https://the-internet.herokuapp.com/upload";

    @Test
    public void uploadFile() {
        driver.get(baseUrl);
        FileUploadPage page = new FileUploadPage(driver, wait);
        page.waitForLoad();

        final String fileName = "testfile.txt";
        Path projectRoot = Paths.get("").toAbsolutePath();
        String filePath = projectRoot.resolve("src/main/resources/files/%s".formatted(fileName)).toString();

        try {
            page.uploadFile(filePath);
        } catch (Exception e) {
            fail("Не удалось загрузить файл: " + e.getMessage());
        }
    }

    @Test
    public void uploadedFileNameIsCorrect() {
        driver.get(baseUrl);
        FileUploadPage page = new FileUploadPage(driver, wait);
        page.waitForLoad();

        final String fileName = "testfile.txt";
        Path projectRoot = Paths.get("").toAbsolutePath();
        String filePath = projectRoot.resolve("src/main/resources/files/%s".formatted(fileName)).toString();

        page.uploadFile(filePath);

        String uploadedFileName = page.getUploadedFileName();

        assertThat(uploadedFileName)
                .as("Имя загруженного файла не соответствует ожидаемому")
                .isEqualTo(fileName);
    }
}
