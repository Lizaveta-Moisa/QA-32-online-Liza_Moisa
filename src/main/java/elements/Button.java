package elements;

import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class Button {
    private WebElement element;

    public Button(WebElement element) {
        this.element = element;
    }

    @Step("Клик по кнопке")
    public void click() {
        element.click();
    }

    public boolean isDisplayed() {
        return element.isDisplayed();
    }
}
