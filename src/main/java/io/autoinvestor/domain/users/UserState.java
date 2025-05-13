package io.autoinvestor.domain.users;

import java.util.Date;

public record UserState(
        UserId userId,
        FirstName firstName,
        LastName lastName,
        UserEmail userEmail,
        Date createdAt,
        Date updatedAt
) {
    public static UserState withUserCreated(UserWasRegisteredEvent event) {
        UserWasRegisteredEventPayload payload = event.getPayload();
        return new UserState((UserId) event.getAggregateId(), payload.firstName(), payload.lastName(), payload.email(), new Date(), new Date());
    }
}
