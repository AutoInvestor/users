package io.autoinvestor.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

public class FirstName {

    private final String firstName;

    FirstName(String firstName) {
        this.firstName = firstName;
    }

    public static FirstName empty() {
        return new FirstName("");
    }

    public static FirstName from(String firstName) {
        return new FirstName(firstName);
    }

    @JsonValue
    public String value() {
        return firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof FirstName that))
            return false;
        return firstName.equals(that.firstName);
    }
}
