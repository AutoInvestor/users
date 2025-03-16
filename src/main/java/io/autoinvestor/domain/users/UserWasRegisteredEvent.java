package io.autoinvestor.domain.users;

import io.autoinvestor.domain.Event;
import io.autoinvestor.domain.Id;

public class UserWasRegisteredEvent extends Event<UserWasRegisteredEventPayload> {

    private UserWasRegisteredEvent(Id aggregateId, UserWasRegisteredEventPayload payload) {
        super(aggregateId, "UserWasRegisteredEvent", payload);
    }

    public static UserWasRegisteredEvent from(User user) {
        UserWasRegisteredEventPayload payload = new UserWasRegisteredEventPayload(user.getName().value());
        return new UserWasRegisteredEvent(user.getId(), payload);
    }
}
