package assertions;

import io.qameta.allure.Step;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AssertionSteps {
    @Step("Проверяем, что кнопка Logout видна: {isVisible}")
    public void checkLogoutButtonVisible(boolean isVisible) {
        assertThat(isVisible)
                .as("После успешного логина должна быть видна кнопка Logout")
                .isTrue();
    }

    @Step("Проверяем, что кнопка Login видна: {isVisible}")
    public void checkLoginButtonVisible(boolean isVisible) {
        assertThat(isVisible)
                .as("После успешного логаута должна отображаться кнопка Login")
                .isTrue();
    }

    @Step("Проверяем текст ошибки при логине: ожидаем '{expected}', получаем '{actual}'")
    public void checkLoginErrorText(String actual, String expected) {
        assertThat(actual)
                .as("При невалидных данных должно отображаться сообщение об ошибке")
                .isEqualTo(expected);
    }

    @Step("Проверяем URL страницы: ожидаем '{expected}', получаем '{actual}'")
    public void checkCurrentUrlContains(String actual, String expected) {
        assertThat(actual)
                .as("URL должен содержать ожидаемое значение")
                .contains(expected);
    }
}
