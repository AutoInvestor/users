package io.autoinvestor.ui;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDTO(String name, String email) {

    @JsonCreator
    public UserDTO(@JsonProperty("name") String name,
                   @JsonProperty("email") String email) {
        this.name = name;
        this.email = email;
    }
}
