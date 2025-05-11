package io.autoinvestor.domain.users;

import io.autoinvestor.domain.Event;
import io.autoinvestor.domain.Id;

public class UserWasRegisteredEvent extends Event<UserWasRegisteredEventPayload> {

    private UserWasRegisteredEvent(Id aggregateId, UserWasRegisteredEventPayload payload) {
        super(aggregateId, "USER_CREATED", payload);
    }

    public static UserWasRegisteredEvent with(UserId userId, FirstName firstName, LastName lastName,
            UserEmail userEmail, UserPassword userPassword, RiskLevel riskLevel) {
        UserWasRegisteredEventPayload payload = new UserWasRegisteredEventPayload(firstName, lastName, userEmail,
                userPassword, riskLevel);
        return new UserWasRegisteredEvent(userId, payload);
    }
}
