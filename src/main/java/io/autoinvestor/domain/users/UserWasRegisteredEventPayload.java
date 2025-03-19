package io.autoinvestor.domain.users;

import io.autoinvestor.domain.EventPayload;
import java.util.Map;

public record UserWasRegisteredEventPayload(UserName name, UserEmail email) implements EventPayload {
    @Override
    public Map<String, Object> asMap() {
        return Map.of(
                "name", name,
                "email", email
        );
    }
}
