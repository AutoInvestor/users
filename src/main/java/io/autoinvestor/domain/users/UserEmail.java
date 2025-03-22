package io.autoinvestor.domain.users;

import io.autoinvestor.exceptions.EmailNotValid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserEmail {
    private final String email;

    public UserEmail(String email) {
        validate(email);
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

    private String value() {
        return this.email;
    }
}
