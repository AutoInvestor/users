package io.autoinvestor.domain.model;

import java.util.Date;

public record UserState(
        UserId userId,
        FirstName firstName,
        LastName lastName,
        UserEmail userEmail,
        RiskLevel riskLevel,
        Date createdAt,
        Date updatedAt) {
    public static UserState empty() {
        return new UserState(
                UserId.generate(),
                FirstName.empty(),
                LastName.empty(),
                UserEmail.empty(),
                RiskLevel.empty(),
                new Date(),
                new Date());
    }

    public UserState withUserCreated(UserWasRegisteredEvent event) {
        UserWasRegisteredEventPayload payload = event.getPayload();
        return new UserState(
                UserId.from(event.getAggregateId().value()),
                FirstName.from(payload.firstName()),
                LastName.from(payload.lastName()),
                UserEmail.from(payload.email()),
                RiskLevel.from(payload.riskLevel()),
                event.getOccurredAt(),
                new Date());
    }

    public UserState withUserDeleted(UserWasUpdatedEvent event) {
        UserWasUpdatedEventPayload payload = event.getPayload();
        return new UserState(
                UserId.from(event.getAggregateId().value()),
                this.firstName,
                this.lastName,
                this.userEmail,
                RiskLevel.from(payload.riskLevel()),
                event.getOccurredAt(),
                new Date());
    }
}
