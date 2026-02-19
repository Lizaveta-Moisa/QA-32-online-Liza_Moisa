import org.testng.annotations.Test;
import page.upload.FramesPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.AssertJUnit.fail;

public class FrameTest extends BaseTest{
    private final String baseUrl = "https://the-internet.herokuapp.com/frames";

    @Test
    public void openIFrame() {
        driver.get(baseUrl);
        FramesPage page = new FramesPage(driver, wait);
        page.waitForLoad();

        try {
            page.switchToFrame1();
        } catch (Exception e) {
            fail("Не удалось переключиться на iFrame (frame1): " + e.getMessage());
        } finally {
            page.switchToDefaultContent();
        }
    }

    @Test
    public void testParagraphTextInsideIFrame() {
        driver.get(baseUrl);
        FramesPage page = new FramesPage(driver, wait);
        page.waitForLoad();

        page.switchToFrame1();

        String paragraphText = page.getParagraphTextInFrame1();

        page.switchToDefaultContent();

        assertThat(paragraphText)
                .as("Текст внутри параграфа в iFrame не совпадает с ожидаемым")
                .isEqualTo("Your content goes here.");
    }
}
