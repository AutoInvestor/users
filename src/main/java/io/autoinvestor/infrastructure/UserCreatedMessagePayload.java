package io.autoinvestor.infrastructure;


import com.fasterxml.jackson.annotation.JsonProperty;

public record UserCreatedMessagePayload(
        @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("email") String email,
        @JsonProperty("riskLevel") Integer riskLevel
) {}
