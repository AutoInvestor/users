package io.autoinvestor.domain.users;

import com.fasterxml.jackson.annotation.JsonValue;

public class LastName {

    private final String lastName;

    public LastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonValue
    public String value() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LastName that))
            return false;
        return lastName.equals(that.lastName);
    }
}
