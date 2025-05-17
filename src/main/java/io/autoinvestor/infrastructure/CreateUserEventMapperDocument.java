package io.autoinvestor.infrastructure;

import io.autoinvestor.domain.Event;
import io.autoinvestor.domain.users.UserWasRegisteredEvent;
import io.autoinvestor.domain.users.UserWasRegisteredEventPayload;
import org.springframework.stereotype.Component;

@Component
public class CreateUserEventMapperDocument {
    UserCreatedEventDocument toDocument(Event<?> userCreatedEvent){
        UserWasRegisteredEventPayload payload = (UserWasRegisteredEventPayload) userCreatedEvent.getPayload();
        return new UserCreatedEventDocument(
                userCreatedEvent.getId().value(),
                userCreatedEvent.getAggregateId().value(),
                payload.firstName().value(),
                payload.lastName().value(),
                payload.email().value(),
                payload.riskLevel().value(),
                userCreatedEvent.getOccurredAt().toInstant(),
                userCreatedEvent.getVersion()

        );
    }
}
