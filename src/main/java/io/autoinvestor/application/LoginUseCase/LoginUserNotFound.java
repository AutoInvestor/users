package io.autoinvestor.application.LoginUseCase;

public class LoginUserNotFound extends RuntimeException {
    private LoginUserNotFound(String message) {
        super(message);
    }

    public static LoginUserNotFound with(String username) {
        String exceptionMessage = "Username " + username + " doesn't exist";
        return new LoginUserNotFound(exceptionMessage);
    }
}
