package elements;

import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class InputField {
    private WebElement element;

    public InputField(WebElement element) {
        this.element = element;
    }

    @Step("Ввод текста: {text}")
    public void type(String text) {
        element.clear();
        element.sendKeys(text);
    }

    public String getText() {
        return element.getAttribute("value");
    }
}
