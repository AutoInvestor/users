package io.autoinvestor.domain.users;

import io.autoinvestor.domain.Event;
import io.autoinvestor.domain.Id;

public class UserWasRegisteredEvent extends Event<UserWasRegisteredEventPayload> {

    private UserWasRegisteredEvent(Id aggregateId, UserWasRegisteredEventPayload payload) {
        super(aggregateId, "UserWasRegisteredEvent", payload);
    }

    public static UserWasRegisteredEvent with(UserId userId, UserName userName, UserEmail userEmail) {
        UserWasRegisteredEventPayload payload = new UserWasRegisteredEventPayload(userName, userEmail);
        return new UserWasRegisteredEvent(userId, payload);
    }
}
