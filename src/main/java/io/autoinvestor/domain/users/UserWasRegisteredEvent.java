package io.autoinvestor.domain.users;

import io.autoinvestor.domain.Event;
import io.autoinvestor.domain.Id;

public class UserWasRegisteredEvent extends Event<UserWasRegisteredEventPayload> {

    private UserWasRegisteredEvent(Id aggregateId, UserWasRegisteredEventPayload payload) {
        super(aggregateId, "UserWasRegisteredEvent", payload);
    }

    public static UserWasRegisteredEvent with(UserId userId, UserName userName, UserEmail userEmail, UserPassword userPassword) {
        UserWasRegisteredEventPayload payload = new UserWasRegisteredEventPayload(userName, userEmail, userPassword);
        return new UserWasRegisteredEvent(userId, payload);
    }
}
