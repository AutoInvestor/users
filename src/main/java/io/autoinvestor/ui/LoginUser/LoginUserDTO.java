package io.autoinvestor.ui.LoginUser;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginUserDTO(String username, String password) {
    @JsonCreator
    public LoginUserDTO(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }
}
