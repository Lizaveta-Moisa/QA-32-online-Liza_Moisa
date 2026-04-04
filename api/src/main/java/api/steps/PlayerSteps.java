package api.steps;

import api.PlayerAPI;
import api.assertions.PlayerAssertions;
import api.models.request.PlayerRequest;
import api.models.response.PlayerResponse;
import io.qameta.allure.Step;

public class PlayerSteps {
    @Step("Создаем игрока с именем {name}")
    public static PlayerResponse createPlayer(String token, String name) {
        PlayerRequest request = new PlayerRequest(name);
        PlayerResponse response = PlayerAPI.createPlayer(token, request);
        PlayerAssertions.assertPlayerCreated(response, name);
        return response;
    }

    @Step("Удаляем игрока с ID {id}")
    public static boolean deletePlayer(String token, int id) {
        PlayerAPI.deletePlayer(token, id);
        return false;
    }
}
