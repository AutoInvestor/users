package io.autoinvestor.domain.model;

public class InvalidPasswordLength extends RuntimeException {
    private InvalidPasswordLength(String message) {
        super(message);
    }

    public static InvalidPasswordLength with(Integer length, Integer minLength) {
        String exceptionMessage =
                "Your current password length of "
                        + length
                        + " is invalid. It "
                        + "should be at least "
                        + minLength
                        + " characters long";
        return new InvalidPasswordLength(exceptionMessage);
    }
}
