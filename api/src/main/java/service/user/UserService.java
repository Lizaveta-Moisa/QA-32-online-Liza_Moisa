package service.user;

import constant.Header;
import entity.Request;
import entity.UserCommon;
import response.user.UserRs;
import io.restassured.response.ValidatableResponse;
import service.ApiService;
import util.TokenUtils;

import java.util.Map;

public class UserService extends ApiService {
    public static final String USERS = "/users";
    public static final String USERS_PATTERN = "/users/%s";
    public static final String GET_USERS_SCHEMA_PATH = "schemas/user/get_users_schema.json";

    /**
     * Получаем список всех пользователей
     *
     * @param userCommon Объект с данными пользователя, включая токен для авторизации
     * @return Список объектов UsersRs, представляющих пользователей
     */
    public ValidatableResponse getUsers(UserCommon userCommon) {
        Map<String, String> headers = Map.of(
                Header.AUTHORIZATION, TokenUtils.createBearer(userCommon.getToken()),
                Header.CONTENT_TYPE, "application/json"
        );
        return get(Request.builder()
                .url(BASE_URL)
                .path(USERS)
                .headers(headers)
                //.schemaName(GET_USERS_SCHEMA_PATH)
                .build()
        );
    }

    /**
     * Обновляем пользователя с указанным идентификатором
     *
     * @param userCommon Объект с данными пользователя, включая токен для авторизации
     * @param usersRs    Объект с данными пользователя, которые нужно обновить
     * @return Объект ValidatableResponse, представляющий ответ на запрос обновления пользователя
     */
    public ValidatableResponse putUsers(UserCommon userCommon,
                                        UserRs usersRs) {
        Map<String, String> headers = Map.of(
                Header.AUTHORIZATION, TokenUtils.createBearer(userCommon.getToken()),
                Header.CONTENT_TYPE, "application/json"
        );
        return put(Request.builder()
                .url(BASE_URL)
                .path(USERS_PATTERN.formatted(usersRs.getId()))
                .body(usersRs)
                .headers(headers)
                .build()
        );
    }
}