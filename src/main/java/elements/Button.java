package elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class Button {
    private final WebElement element;

    public Button(WebElement element) {
        this.element = element;
    }

    @Step("Клик по кнопке")
    public void click() {
        element.click();
    }
}
