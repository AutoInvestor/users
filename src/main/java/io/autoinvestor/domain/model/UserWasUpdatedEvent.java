package io.autoinvestor.domain.model;

import io.autoinvestor.domain.Id;
import io.autoinvestor.domain.events.Event;
import io.autoinvestor.domain.events.EventId;

import java.util.Date;

public class UserWasUpdatedEvent extends Event<UserWasUpdatedEventPayload>{
    public static final String TYPE = "SUBSCRIPTION_UPDATED";

    private UserWasUpdatedEvent(Id aggregateId, UserWasUpdatedEventPayload payload) {
        super(aggregateId, TYPE, payload);
    }

    protected UserWasUpdatedEvent(
            EventId id,
            Id aggregateId,
            UserWasUpdatedEventPayload payload,
            Date occurredAt,
            int version) {
        super(id, aggregateId, TYPE, payload, occurredAt, version);
    }

    public static UserWasUpdatedEvent with (UserId userId,
                        RiskLevel riskLevel) {
        UserWasUpdatedEventPayload payload = new UserWasUpdatedEventPayload(
                riskLevel.value());
        return new UserWasUpdatedEvent(userId, payload);
    }

    public static UserWasUpdatedEvent hydrate (EventId id,
                                               Id aggregateId,
                                               UserWasUpdatedEventPayload payload,
                                               Date occurredAt,
                                               int version){
        return new UserWasUpdatedEvent(id, aggregateId, payload, occurredAt, version);
    }
}
