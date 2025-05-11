package io.autoinvestor.domain.users;

import com.fasterxml.jackson.annotation.JsonValue;

public class FirstName {

    private final String firstName;

    public FirstName(String firstName) {
        this.firstName = firstName;
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
