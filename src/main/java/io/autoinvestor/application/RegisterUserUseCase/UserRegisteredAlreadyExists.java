package io.autoinvestor.application.RegisterUserUseCase;

public class UserRegisteredAlreadyExists extends RuntimeException {
    private UserRegisteredAlreadyExists(String message) {
        super(message);
    }

    public static UserRegisteredAlreadyExists with(String email) {
        String exceptionMessage = "Invalid user. " + email + " already exists.";
        return new UserRegisteredAlreadyExists(exceptionMessage);
    }
}
