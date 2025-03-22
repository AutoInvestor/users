package io.autoinvestor.domain.users;

import io.autoinvestor.domain.EventPayload;
import java.util.Map;

public record UserWasRegisteredEventPayload(UserName username, UserEmail email, UserPassword userPassword) implements EventPayload {
    @Override
    public Map<String, Object> asMap() {
        return Map.of(
                "username", username,
                "email", email,
                "password", userPassword
        );
    }
}
