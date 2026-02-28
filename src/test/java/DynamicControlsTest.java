import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import page.upload.DynamicControlsPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.testng.AssertJUnit.fail;

public class DynamicControlsTest extends BaseTest{

    private final String baseUrl = "https://the-internet.herokuapp.com/dynamic_controls";

    @Test
    public void clickRemoveButton(){
        driver.get(baseUrl);
        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.waitForLoad();

        try {
            page.clickRemove();
        } catch (Exception e) {
            fail("Не удалось нажать кнопку Remove: " + e.getMessage());
        }
    }

    @Test
    public void waitForItsGoneText(){
        driver.get(baseUrl);

        DynamicControlsPage page = new DynamicControlsPage(driver, wait);

        page.clickRemove();

        String message = page.waitForMessage("It's gone!");

        assertThat(message)
                .as("Ожидался текст 'It's gone!', но получили: %s", message)
                .contains("It's gone!");
    }

    @Test
    public void checkboxIsNotDisplayed(){
        driver.get(baseUrl);

        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.waitForLoad();
        page.clickRemove();
        page.waitForMessage("It's gone!");

        assertThat(page.isCheckboxDisplayed())
                .as("Чекбокс должен отсутствовать на странице после удаления")
                .isFalse();
    }

    @Test
    public void findInput() {
        driver.get(baseUrl);

        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.waitForLoad();

        WebElement input = null;
        try {
            input = page.getInput();
        } catch (Exception e) {
            fail("Не удалось найти input: " + e.getMessage());
        }
        assertThat(input)
                .as("Input должен присутствовать на странице")
                .isNotNull();
    }

    @Test
    public void inputIsDisabled() {
        driver.get(baseUrl);

        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.waitForLoad();

        WebElement input = page.getInput();

        assertThat(input.isEnabled())
                .as("Ожидалось что input изначально disabled")
                .isFalse();
    }

    @Test
    public void clickButtonToEnableInput() {
        driver.get(baseUrl);

        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.waitForLoad();

        assertThatCode(page::clickInput)
                .as("Не удалось нажать кнопку для включения input")
                .doesNotThrowAnyException();
    }

    @Test
    public void waitForItsEnabledText() {
        driver.get(baseUrl);

        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.waitForLoad();
        page.clickRemove();
        page.waitForMessage("It's gone!");
        page.clickInput();

        String message = page.waitForMessage("It's enabled!");

        assertThat(message)
                .as("Ожидался текст 'It's enabled!', но получили: %s", message)
                .contains("It's enabled!");
    }

    @Test
    public void inputIsEnabled() {
        driver.get(baseUrl);

        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.waitForLoad();

        page.clickRemove();
        page.waitForMessage("It's gone!");

        page.clickInput();
        page.waitForMessage("It's enabled!");

        WebElement input = page.getInput();

        assertThat(input.isEnabled())
                .as("Ожидалось, что input будет enabled после нажатия кнопки")
                .isTrue();
    }
}
