package tests;

import annotations.TestType;
import base.BaseTest;
import enums.TestCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

@TestType(TestCategory.REGRESSION)
public class InternetTests extends BaseTest {
    private static final String BASE_URL = "https://the-internet.herokuapp.com";
    private static final String CHECKBOXES_URL = BASE_URL + "/checkboxes";
    private static final String ADD_REMOVE_URL = BASE_URL + "/add_remove_elements/";
    private static final String DROPDOWN_URL = BASE_URL + "/dropdown";
    private static final String INPUTS_URL = BASE_URL + "/inputs";
    private static final By CHECKBOX_LOCATOR = By.cssSelector("input[type='checkbox']");
    private static final By ADD_BUTTON = By.cssSelector("button");
    private static final By ADDED_ELEMENTS = By.cssSelector(".added-manually");
    private static final By DROPDOWN_OPTIONS = By.cssSelector("#dropdown option");
    private static final By INPUT_FIELD = By.cssSelector("input");

    @Test
    @TestType(TestCategory.SMOKE)
    void checkPageTitle() {
        driver.get(BASE_URL);
        String title = driver.getTitle();
        Assertions.assertThat(title).as("Заголовок страницы должен содержать текст 'The Internet'").contains("The Internet");
    }

    @Test
    void checkCheckboxPage() {
        driver.get(CHECKBOXES_URL);
        int count = driver.findElements(CHECKBOX_LOCATOR).size();
        Assertions.assertThat(count).as("На странице чекбоксов должно быть ровно 2 чекбокса").isEqualTo(2);
    }

    @Test
    @TestType({TestCategory.SMOKE, TestCategory.UI})
    void checkAddRemoveElements() {
        driver.get(ADD_REMOVE_URL);
        driver.findElement(ADD_BUTTON).click();
        int buttons = driver.findElements(ADDED_ELEMENTS).size();
        Assertions.assertThat(buttons).as("После нажатия 'Add Element' должна появиться одна кнопка удаления").isEqualTo(1);
    }

    @Test
    void checkDropdownOptions() {
        driver.get(DROPDOWN_URL);
        int options = driver.findElements(DROPDOWN_OPTIONS).size();
        Assertions.assertThat(options).as("В выпадающем списке должно быть больше одного элемента").isGreaterThan(1);
    }

    @Test
    @TestType(TestCategory.SMOKE)
    void checkInputsPage() {
        driver.get(INPUTS_URL);
        driver.findElement(INPUT_FIELD).sendKeys("10");
        String value = driver.findElement(INPUT_FIELD).getAttribute("value");
        Assertions.assertThat(value).as("Поле ввода должно содержать введённое значение 10").isEqualTo("10");
    }
}
