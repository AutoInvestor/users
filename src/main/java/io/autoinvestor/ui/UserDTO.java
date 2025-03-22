package io.autoinvestor.ui;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDTO(String username, String email, String password) {

    @JsonCreator
    public UserDTO(@JsonProperty("username") String username,
                   @JsonProperty("email") String email,
                    @JsonProperty("password") String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
