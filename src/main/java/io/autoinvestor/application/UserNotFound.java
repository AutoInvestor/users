package io.autoinvestor.application;

public class UserNotFound extends RuntimeException {
    private UserNotFound(String message) {
        super(message);
    }

    public static UserNotFound with(String name) {
        String message = "User with id/email " + name + " doesn't exist.";
        return new UserNotFound(message);
    }
}
