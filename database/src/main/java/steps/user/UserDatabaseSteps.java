package steps.user;

import entity.user.UserEntity;
import io.qameta.allure.Step;
import service.user.UserDatabaseService;

public class UserDatabaseSteps {
    private final UserDatabaseService userDatabaseService;

    public UserDatabaseSteps(UserDatabaseService userDatabaseService) {
        this.userDatabaseService = userDatabaseService;
    }

    @Step("Получаем запись в БД с 'id' = '{id}' в таблице 'users'")
    public UserEntity getById(Long id) {
        return userDatabaseService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User with id = " + id + " not found"));
    }
}
