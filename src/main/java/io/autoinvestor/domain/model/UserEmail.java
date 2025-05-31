package io.autoinvestor.domain.model;

import io.autoinvestor.exceptions.EmailNotValid;

import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonValue;

public class UserEmail {
    private static final String ATOM = "[\\w+&*-]";
    private static final String LABEL = "[A-Za-z0-9-]";
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile(
                    "^"
                            + ATOM
                            + "{1,64}(?:\\."
                            + ATOM
                            + "{1,64})*@"
                            + LABEL
                            + "{1,63}(?:\\."
                            + LABEL
                            + "{1,63})+"
                            + "[A-Za-z]{2,7}$");

    private final String email;

    UserEmail(String email) {
        this.email = email;
    }

    private static void validate(String email) {
        boolean valid = EMAIL_PATTERN.matcher(email).matches();
        if (!valid) {
            throw EmailNotValid.with(email);
        }
    }

    public static UserEmail empty() {
        return new UserEmail("");
    }

    public static UserEmail from(String email) {
        validate(email);
        return new UserEmail(email);
    }

    @JsonValue
    public String value() {
        return this.email;
    }
}
