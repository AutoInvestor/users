package io.autoinvestor.ui.RegisterUser;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterUserDTO(String firstName, String lastName, String email, String password, Integer riskLevel) {

    @JsonCreator
    public RegisterUserDTO(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email, @JsonProperty("password") String password,
            @JsonProperty("riskLevel") Integer riskLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.riskLevel = riskLevel;
    }
}
