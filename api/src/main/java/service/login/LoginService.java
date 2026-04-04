package service.login;

import constant.Header;
import entity.Request;
import request.user.UserRq;
import response.login.AuthTokenRs;
import service.user.UserService;

import java.util.Map;

public class LoginService extends UserService {
    public static final String AUTH_TOKEN = "auth/sign-up";

    public static final String POST_AUTH_TOKEN_SCHEMA_PATH = "schemas/login/post_auth_token_schema.json";

    public AuthTokenRs login(UserRq userRq) {
        Map<String, String> headers = Map.of(
                Header.CONTENT_TYPE, "application/json"
        );
        return post(Request.builder()
                .url(BASE_URL)
                .path(AUTH_TOKEN)
                .body(userRq)
                .headers(headers)
                .clazz(AuthTokenRs.class)
                //.schemaName(POST_AUTH_TOKEN_SCHEMA_PATH)
                .build()
        );
    }
}