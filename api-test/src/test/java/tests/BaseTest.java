package tests;

import api.AuthAPI;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {
    protected static String token;
    protected static String baseUrl;

    @BeforeAll
    @Step("Инициализация тестов: получение токена и базового URL")
    static void init() {
        baseUrl = ConfigReader.get("base.url");
        token = AuthAPI.getToken(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
    }
}
