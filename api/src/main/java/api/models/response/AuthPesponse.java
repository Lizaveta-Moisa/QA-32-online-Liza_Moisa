package api.models.response;

public class AuthPesponse {
    private String token; // токен, который вернул сервер

    public void AuthResponse() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
