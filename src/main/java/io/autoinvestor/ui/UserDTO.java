package io.autoinvestor.ui;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDTO(String name) {

    @JsonCreator
    public UserDTO(@JsonProperty("name") String name) {
        this.name = name;
    }
}
