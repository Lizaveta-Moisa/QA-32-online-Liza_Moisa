package tests.user;

import assertion.UserDatabaseAssertionSteps;
import com.github.javafaker.Faker;
import entity.UserCommon;
import entity.user.UserEntity;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import request.user.UserRq;
import response.user.UserRs;
import service.login.LoginService;
import service.user.UserDatabaseService;
import service.user.UserService;
import steps.login.LoginMixedSteps;
import steps.user.UserDatabaseSteps;
import steps.user.UserSteps;

import java.util.List;

public class UserTest {

    @Test
    @AllureId("API-004")
    @Description("Проверка получения всех пользователей через API")
    public void getAllUsers() {
        LoginMixedSteps loginMixedSteps = new LoginMixedSteps(new LoginService());
        UserSteps userSteps = new UserSteps(new UserService());
        UserDatabaseSteps userDatabaseSteps = new UserDatabaseSteps(new UserDatabaseService());

        UserRq userRq = UserRq.builder()
                .username(Faker.instance().name().username())
                .password("123")
                .build();

        UserCommon userCommon = loginMixedSteps.login(userRq);
        List<UserRs> usersRs = userSteps.getAll(userCommon);
        if (usersRs.isEmpty()) {
            throw new AssertionError("Список пользователей пустой");
        }
        UserRs randomUserRs = usersRs.get(0);
        UserEntity userEntity = userDatabaseSteps.getById(randomUserRs.getId());
        new UserDatabaseAssertionSteps(userEntity)
                .assertUserName(randomUserRs.getUsername())
                .assertAll();
    }

    @Test
    @AllureId("API-005")
    @Description("Проверка получения пользователя по ID")
    public void getUserById_Valid() {
        LoginMixedSteps loginMixedSteps = new LoginMixedSteps(new LoginService());
        UserSteps userSteps = new UserSteps(new UserService());
        UserDatabaseSteps userDatabaseSteps = new UserDatabaseSteps(new UserDatabaseService());

        UserRq userRq = UserRq.builder()
                .username(Faker.instance().name().username())
                .password("123")
                .build();

        UserCommon userCommon = loginMixedSteps.login(userRq);

        List<UserRs> usersRs = userSteps.getAll(userCommon);
        UserRs randomUserRs = usersRs.get(0);

        UserRs userFromApi = userSteps.getById(userCommon, randomUserRs.getId());
        UserEntity userEntity = userDatabaseSteps.getById(randomUserRs.getId());

        new UserDatabaseAssertionSteps(userEntity)
                .assertUserName(userFromApi.getUsername())
                .assertAll();
    }

    @Test
    @AllureId("API-006")
    @Description("Проверка получения пользователя с несуществующим ID")
    public void getUserByIdInvalidId() {
        LoginMixedSteps loginMixedSteps = new LoginMixedSteps(new LoginService());
        UserSteps userSteps = new UserSteps(new UserService());

        UserRq userRq = UserRq.builder()
                .username(Faker.instance().name().username())
                .password("123")
                .build();

        UserCommon userCommon = loginMixedSteps.login(userRq);

        long invalidId = 999999;

        try {
            userSteps.getById(userCommon, invalidId);
            throw new AssertionError("Ожидалась ошибка при получении несуществующего пользователя");
        } catch (Exception e) {
        }
    }
}
