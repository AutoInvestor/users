package io.autoinvestor.domain.users;

import io.autoinvestor.domain.EventPayload;
import java.util.Map;

public record UserWasRegisteredEventPayload(String name) implements EventPayload {
    @Override
    public Map<String, Object> asMap() {
        return Map.of("name", name);
    }
}
