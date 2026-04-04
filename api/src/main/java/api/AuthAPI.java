package api;

import api.models.request.AuthRequest;
import api.models.response.AuthPesponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthAPI {
    private static final String BASE_URL = "http://localhost:5030";

    public static String getToken(String username, String password) {
        AuthRequest request = new AuthRequest(username, password);

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(request)
                .post("/auth/token");

        response.then().statusCode(200);

        AuthPesponse authResponse = response.as(AuthPesponse.class);
        return authResponse.getToken();
    }
}
