package io.autoinvestor.domain.model;

import io.autoinvestor.domain.events.EventPayload;

import java.util.Map;

public record UserWasUpdatedEventPayload(
        int riskLevel
) implements EventPayload {
    @Override
    public Map<String, Object> asMap() {
        return Map.of(
                "riskLevel", riskLevel
        );
    }
}
