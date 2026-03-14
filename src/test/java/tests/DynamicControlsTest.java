package tests;

import annotations.TestType;
import extensions.TestTypeExtension;
import org.example.pages.DynamicControlsPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.fail;

@TestType(TestType.Type.UI)
@ExtendWith(TestTypeExtension.class)
public class DynamicControlsTest extends BaseTest {

    private final String  baseUrl = "https://the-internet.herokuapp.com/dynamic_controls";

    @Test
    @TestType(TestType.Type.SMOKE)
    public void waitForItsGoneText(){
        driver.get( baseUrl);
        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.clickRemove();

        String message = page.waitForMessage("It's gone!");
        assertThat(message)
                .as("Ожидался текст 'It's gone!', но получили: %s", message)
                .contains("It's gone!");
    }

    @Test
    @TestType(TestType.Type.REGRESSION)
    public void checkboxIsNotDisplayedTest(){
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
    @TestType(TestType.Type.UI)
    public void findInputTest() {
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
    @TestType(TestType.Type.UI)
    public void inputIsDisabledTest() {
        driver.get(baseUrl);

        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.waitForLoad();

        WebElement input = page.getInput();

        assertThat(input.isEnabled())
                .as("Ожидалось что input изначально disabled")
                .isFalse();
    }

    @Test
    @TestType(TestType.Type.REGRESSION)
    public void clickButtonToEnableInputTest() {
        driver.get(baseUrl);

        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.waitForLoad();

        assertThatCode(page::clickInput)
                .as("Не удалось нажать кнопку для включения input")
                .doesNotThrowAnyException();
    }

    @Test
    @TestType(TestType.Type.SMOKE)
    public void waitForItsEnabledTextTest() {
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
    @TestType(TestType.Type.UI)
    public void inputIsEnabledTest() {
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
