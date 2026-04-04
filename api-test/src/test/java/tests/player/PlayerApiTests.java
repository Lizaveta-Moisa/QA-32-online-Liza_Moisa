package tests.player;

import api.assertions.PlayerAssertions;
import api.models.response.PlayerResponse;
import api.steps.PlayerSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import tests.BaseTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("Player API")
@Feature("CRUD Players")
public class PlayerApiTests extends BaseTest {

    public static int createdPlayerId;

    @Test
    @Order(1)
    @Story("API-001 Создание нового игрока с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    void api001_createPlayerWithValidData() {
        String name = "Player_" + System.currentTimeMillis();
        PlayerResponse player = PlayerSteps.createPlayer(token, name);
        PlayerAssertions.assertPlayerCreated(player, name);
        createdPlayerId = player.getId();
    }

}
