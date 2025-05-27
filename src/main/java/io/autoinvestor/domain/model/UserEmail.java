package io.autoinvestor.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
import io.autoinvestor.exceptions.EmailNotValid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserEmail {
    private final String email;

    UserEmail(String email) {
        this.email = email;
    }

    private static void validate(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
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
