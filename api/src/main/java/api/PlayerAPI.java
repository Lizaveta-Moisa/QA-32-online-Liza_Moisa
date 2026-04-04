package api;


import api.models.request.PlayerRequest;
import api.models.response.PlayerResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;

public class PlayerAPI {
    private static final String BASE_URL = "http://localhost:5030";

    public static PlayerResponse createPlayer(String token, PlayerRequest request) {
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(request)
                .post("/players");

        response.then().statusCode(201);
        return response.as(PlayerResponse.class);
    }

    public static List<PlayerResponse> getAllPlayers(String token) {
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .get("/players");

        response.then().statusCode(200);
        return response.jsonPath().getList("", PlayerResponse.class);
    }

    public static PlayerResponse getPlayerById(String token, int id) {
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .get("/players/" + id);

        response.then().statusCode(200);
        return response.as(PlayerResponse.class);
    }

    public static void deletePlayer(String token, int id) {
        RestAssured.given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .delete("/players/" + id)
                .then().statusCode(204);
    }
}
