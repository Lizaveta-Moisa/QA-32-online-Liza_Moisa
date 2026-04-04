package api.assertions;

import api.models.response.PlayerResponse;
import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerAssertions {
    @Step("Проверяем, что игрок создан с именем: {expectedName}")
    public static void assertPlayerCreated(PlayerResponse player, String expectedName) {

        assertThat(player)
                .as("Ответ с игроком не должен быть null")
                .isNotNull();

        assertThat(player.getId())
                .as("ID игрока должен быть положительным")
                .isPositive();

        assertThat(player.getName())
                .as("Имя игрока должно совпадать с ожидаемым")
                .isEqualTo(expectedName);
    }

}
