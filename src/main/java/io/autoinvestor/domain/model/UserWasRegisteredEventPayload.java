package io.autoinvestor.domain.model;

import io.autoinvestor.domain.events.EventPayload;

import java.util.Map;

public record UserWasRegisteredEventPayload(
        String firstName, String lastName, String email, int riskLevel) implements EventPayload {
    @Override
    public Map<String, Object> asMap() {
        return Map.of(
                "firstName",
                firstName,
                "lastName",
                lastName,
                "email",
                email,
                "riskLevel",
                riskLevel);
    }
}
