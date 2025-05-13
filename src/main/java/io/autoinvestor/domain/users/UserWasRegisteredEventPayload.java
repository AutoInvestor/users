package io.autoinvestor.domain.users;

import io.autoinvestor.domain.EventPayload;

import java.util.Map;

public record UserWasRegisteredEventPayload(
        FirstName firstName,
        LastName lastName,
        UserEmail email,
        RiskLevel riskLevel
) implements EventPayload {

    @Override
    public Map<String, Object> asMap() {
        return Map.of("firstName", firstName, "lastName", lastName, "email", email, "riskLevel", riskLevel);
    }
}
