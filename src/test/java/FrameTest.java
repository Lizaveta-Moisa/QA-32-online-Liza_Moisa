import org.testng.annotations.Test;
import page.upload.FramesPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.AssertJUnit.fail;

public class FrameTest extends BaseTest{

    private static final String URL = "https://the-internet.herokuapp.com/frames"; // замените на актуальный URL
    private static final String EXPECTED_TEXT = "Your content goes here.";

    @Test
    public void openIframeAndCheckText() {
        driver.get(URL);
        FramesPage page = new FramesPage(driver, wait);
        page.waitForLoad();

        page.switchToFrame1();

        String actualText = page.getParagraphTextInFrame1();

        assertThat(actualText)
                .as("Проверка текста внутри iframe")
                .isEqualTo(EXPECTED_TEXT);
    }
}
