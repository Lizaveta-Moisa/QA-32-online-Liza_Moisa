package error.user;

public class UserErrorResponse {
    private final String message;

    private final int code;

    public UserErrorResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public UserErrorResponse assertErrorMessage(String expectedMessage) {
        if (message == null || !message.equals(expectedMessage)) {
            throw new AssertionError(
                    "Ожидалось сообщение ошибки: " + expectedMessage + ", но было: " + message
            );
        }
        return this;
    }

    public int getCode() {
        return code;
    }
}
