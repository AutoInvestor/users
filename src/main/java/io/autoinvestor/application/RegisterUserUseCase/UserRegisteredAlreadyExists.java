package io.autoinvestor.application.RegisterUserUseCase;

public class UserRegisteredAlreadyExists extends RuntimeException {
    private UserRegisteredAlreadyExists(String message) {
        super(message);
    }

    public static UserRegisteredAlreadyExists with(String username) {
        String exceptionMessage = "Invalid username. " + username + " already exists.";
        return new UserRegisteredAlreadyExists(exceptionMessage);
    }
}
