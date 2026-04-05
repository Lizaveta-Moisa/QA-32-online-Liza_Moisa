package steps.login;

import entity.UserCommon;
import entity.user.UserEntity;
import enums.Role;
import error.user.UserErrorResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import request.user.UserRq;
import response.login.AuthTokenRs;
import service.login.LoginService;
import service.user.UserDatabaseService;

public class LoginMixedSteps {

    private final LoginService loginService;

    public LoginMixedSteps(LoginService loginService) {
        this.loginService = loginService;
    }

    @Step("Авторизируемся пользователем с 'username' = '{userRq.username}'")
    public UserCommon login(UserRq userRq) {
        AuthTokenRs authTokenRs = loginService.login(userRq);
        UserEntity userEntity = new UserDatabaseService().getUserByUsername(userRq.getUsername()).get();
        userEntity.setRole(Role.ADMIN);
        new UserDatabaseService().updateUser(userEntity);
        return UserCommon.builder()
                .token(authTokenRs.getToken())
                .username(userRq.getUsername())
                .build();
    }
}
