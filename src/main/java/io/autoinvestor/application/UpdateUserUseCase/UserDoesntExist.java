package io.autoinvestor.application.UpdateUserUseCase;

public class UserDoesntExist extends RuntimeException {
    private UserDoesntExist(String message) {
        super(message);
    }

    public static UserDoesntExist with(String userId) {
        String message = "User with id " + userId + "doesn't exist";
        return new UserDoesntExist(message);
    }
}
