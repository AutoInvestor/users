package io.autoinvestor.domain.model;

import io.autoinvestor.domain.Id;
import io.autoinvestor.domain.events.Event;
import io.autoinvestor.domain.events.EventId;

import java.util.Date;

public class UserWasRegisteredEvent extends Event<UserWasRegisteredEventPayload> {

    public static final String TYPE = "USER_CREATED";

    private UserWasRegisteredEvent(
            Id aggregateId, UserWasRegisteredEventPayload payload, int version) {
        super(aggregateId, TYPE, payload, version);
    }

    protected UserWasRegisteredEvent(
            EventId id,
            Id aggregateId,
            UserWasRegisteredEventPayload payload,
            Date occurredAt,
            int version) {
        super(id, aggregateId, TYPE, payload, occurredAt, version);
    }

    public static UserWasRegisteredEvent with(
            UserId userId,
            FirstName firstName,
            LastName lastName,
            UserEmail userEmail,
            RiskLevel riskLevel,
            int version) {
        var payload =
                new UserWasRegisteredEventPayload(
                        firstName.value(), lastName.value(), userEmail.value(), riskLevel.value());
        return new UserWasRegisteredEvent(userId, payload, version);
    }

    public static UserWasRegisteredEvent hydrate(
            EventId id,
            Id aggregateId,
            UserWasRegisteredEventPayload payload,
            Date occurredAt,
            int version) {
        return new UserWasRegisteredEvent(id, aggregateId, payload, occurredAt, version);
    }
}
