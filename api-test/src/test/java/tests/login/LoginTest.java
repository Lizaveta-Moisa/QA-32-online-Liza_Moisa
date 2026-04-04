package tests.login;

import assertion.UserDatabaseAssertionSteps;
import com.github.javafaker.Faker;
import entity.UserCommon;
import entity.user.UserEntity;
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

public class LoginTest {

    @Test
    public void updateUser(){
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
}
