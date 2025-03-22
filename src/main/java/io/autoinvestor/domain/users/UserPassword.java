package io.autoinvestor.domain.users;

import io.autoinvestor.application.PasswordService;

public class UserPassword {

    private static final Integer MIN_LENGTH = 4;
    private final String encryptedPassword;

    private UserPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public static UserPassword create(String rawPassword) {
        validate(rawPassword);
        String encryptedPassword = PasswordService.hashPassword(rawPassword);
        return new UserPassword(encryptedPassword);
    }

    public static void validate(String rawPassword) {
        if (rawPassword.length() < MIN_LENGTH) {
            throw InvalidPasswordLength.with(rawPassword.length(), MIN_LENGTH);
        }
    }

    private String value() {
        return this.encryptedPassword;
    }
}
