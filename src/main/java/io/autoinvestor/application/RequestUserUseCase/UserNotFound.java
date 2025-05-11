package io.autoinvestor.application.RequestUserUseCase;

public class UserNotFound extends RuntimeException {
    private UserNotFound(String message) {
        super(message);
    }

    public static UserNotFound with(String email) {
        String message = "User with mail " + email + " doesn't exist.";
        return new UserNotFound(message);
    }
}
