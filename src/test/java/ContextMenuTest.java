import org.testng.annotations.Test;
import page.upload.ContextMenuPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.AssertJUnit.fail;

public class ContextMenuTest extends BaseTest {
    private final String baseUrl = "https://the-internet.herokuapp.com/context_menu";

    @Test
    public void rightClickOneElement() {
        driver.get(baseUrl);
        ContextMenuPage page = new ContextMenuPage(driver, wait);
        page.waitForLoad();
        try {
            page.openContextMenu();
        } catch (Exception e) {
            fail("Ошибка при выполнении правого клика по элементу: " + e.getMessage());
        }

    }
    @Test
    public void alertTextValidation() {
        driver.get(baseUrl);
        ContextMenuPage page = new ContextMenuPage(driver, wait);
        page.waitForLoad();

        page.openContextMenu();

        String alertText = page.getAlertText();

        assertThat(alertText)
                .as("Текст алерта не соответствует ожидаемому")
                .isEqualTo("You selected a context menu");
    }

    @Test
    private void testAlertClose(){
        driver.get(baseUrl);
        ContextMenuPage page = new ContextMenuPage(driver, wait);
        page.waitForLoad();

        page.openContextMenu();

        page.getAlertText();

        try{
            page.closeAlert();
        }catch (Exception e){
            fail("Ошибка при закрытии алерта: " + e.getMessage());
        }
        boolean alertClosed;
        try {
            driver.switchTo().alert();
            alertClosed = false;
        } catch (org.openqa.selenium.NoAlertPresentException ex) {
            alertClosed = true;
        }
        assertThat(alertClosed)
                .as("Алерт должен быть закрыт, но он всё ещё отображается")
                .isTrue();
    }
}
