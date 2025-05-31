package io.autoinvestor.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

public class LastName {

    private final String lastName;

    LastName(String lastName) {
        this.lastName = lastName;
    }

    public static LastName empty() {
        return new LastName("");
    }

    public static LastName from(String lastName) {
        return new LastName(lastName);
    }

    @JsonValue
    public String value() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LastName that)) return false;
        return lastName.equals(that.lastName);
    }
}
