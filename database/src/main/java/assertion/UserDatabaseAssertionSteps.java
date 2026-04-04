package assertion;

import entity.user.UserEntity;
import io.qameta.allure.Step;

public class UserDatabaseAssertionSteps extends AssertionSteps{
    private final UserEntity userEntity;

    public UserDatabaseAssertionSteps(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Step("Проверяем, что имя пользователя в БД равно '{username}'")
    public UserDatabaseAssertionSteps assertUserName(String username) {
        softAssert().assertThat(userEntity.getUsername())
                .as("Имя пользователя в БД не соответствует ожидаемому")
                .isEqualTo(username);
        return this;
    }
}
