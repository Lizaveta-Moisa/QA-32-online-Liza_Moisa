package tests.login;

import assertion.UserDatabaseAssertionSteps;
import com.github.javafaker.Faker;
import entity.UserCommon;
import entity.user.UserEntity;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import request.user.UserRq;
import response.user.UserRs;
import service.login.LoginService;
import service.user.UserDatabaseService;
import service.user.UserService;
import steps.login.LoginMixedSteps;
import steps.user.UserDatabaseSteps;
import steps.user.UserSteps;
import util.RandomUtils;

import java.util.List;

public class LoginTest {

    @Test
    @AllureId("API-001")
    @Description("Проверка успешного обновления username существующего пользователя через API")
    public void updateUser() {
        LoginMixedSteps loginMixedSteps = new LoginMixedSteps(new LoginService());
        UserSteps userSteps = new UserSteps(new UserService());
        String username = Faker.instance().name().username();
        UserDatabaseSteps userDatabaseSteps = new UserDatabaseSteps(new UserDatabaseService());
        UserRq userRq = UserRq.builder()
                .username(username)
                .password("123")
                .build();
        UserCommon userCommon = loginMixedSteps.login(userRq);
        List<UserRs> usersRs = userSteps.getAll(userCommon);
        UserRs randomUserRs = usersRs.stream()
                .filter(s -> !s.getUsername().equals(userRq.getUsername()))
                .findAny()
                .orElse(null);
        String newUserName = Faker.instance().name().username();
        randomUserRs.setUsername(newUserName);
        userSteps.update(userCommon, randomUserRs);
        UserEntity userEntity = userDatabaseSteps.getById(randomUserRs.getId());
        new UserDatabaseAssertionSteps(userEntity)
                .assertUserName(newUserName)
                .assertAll();
    }

    @Test
    @AllureId("API-002")
    @Description("Попытка обновить username на уже существующий")
    public void updateUserToExistingUsername() {
        LoginMixedSteps loginMixedSteps = new LoginMixedSteps(new LoginService());
        UserSteps userSteps = new UserSteps(new UserService());

        UserCommon userCommon = loginMixedSteps.login(UserRq.builder()
                .username(Faker.instance().name().username())
                .password("123")
                .build());

        List<UserRs> users = userSteps.getAll(userCommon);
        if (users.size() < 2) return;

        UserRs user1 = users.get(0);
        UserRs user2 = users.get(1);

        user2.setUsername(user1.getUsername());
        userSteps.updateExpectingError(userCommon, user2)
                .assertErrorMessage("User with this username already exists");
    }

    @Test
    @AllureId("API-003")
    @Description("Проверка, что система корректно авторизует пользователя с очень длинным username")
    public void loginLongUsername() {
        LoginMixedSteps loginMixedSteps = new LoginMixedSteps(new LoginService());
        String maxLengthUsername = "user_" + RandomUtils.randomString(45);

        UserCommon userCommon = loginMixedSteps.login(UserRq.builder()
                .username(maxLengthUsername)
                .password("123")
                .build());

        Assertions.assertNotNull(userCommon.getToken(), "Токен должен быть получен для username длиной 50 символов");
    }
}
