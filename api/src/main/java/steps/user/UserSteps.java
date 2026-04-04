package steps.user;

import entity.UserCommon;
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
}
