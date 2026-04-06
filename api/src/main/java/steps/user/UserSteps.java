package steps.user;

import entity.UserCommon;
import error.user.UserErrorResponse;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import response.user.UserRs;
import service.user.UserService;

import java.util.Arrays;
import java.util.List;

public class UserSteps {
    private final UserService userService;

    public UserSteps(UserService userService) {
        this.userService = userService;
    }

    @Step("Получаем список всех пользователей")
    public List<UserRs> getAll(UserCommon userCommon) {
        return Arrays.asList(userService.getUsers(userCommon)
                .extract().as(UserRs[].class));
    }

    @Step("Обновляем пользователя c 'id' = '{usersRs.id}'")
    public ValidatableResponse update(UserCommon userCommon,
                                      UserRs usersRs) {
        return userService.putUsers(userCommon, usersRs);
    }

    @Step("Пытаемся обновить пользователя и ожидаем ошибку")
    public UserErrorResponse updateExpectingError(UserCommon userCommon, UserRs userRs) {
        ValidatableResponse response = userService.putUsers(userCommon, userRs);

        int statusCode = response.extract().statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            throw new AssertionError("Ожидалась ошибка при обновлении пользователя, но она не произошла");
        }

        String errorMessage = response.extract().jsonPath().getString("error");

        return new UserErrorResponse(errorMessage, statusCode);
    }

    @Step("Получаем пользователя по ID = {userId}")
    public UserRs getById(UserCommon userCommon, long userId) {
        ValidatableResponse response = userService.getUserById(userCommon, userId);

        int statusCode = response.extract().statusCode();
        if (statusCode == 404) {
            throw new RuntimeException("Пользователь с ID " + userId + " не найден");
        }
        else if (statusCode >= 400) {
            throw new RuntimeException("Ошибка при получении пользователя: " + statusCode);
        }

        return response.extract().as(UserRs.class);
    }
}
