package elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class InputField {
    private final WebElement element;

    public InputField(WebElement element) {
        this.element = element;
    }

    @Step("Ввод текста: {text}")
    public void type(String text) {
        element.clear();
        element.sendKeys(text);
    }
}
