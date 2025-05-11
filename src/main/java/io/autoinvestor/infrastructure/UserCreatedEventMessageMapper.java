package io.autoinvestor.infrastructure;

import io.autoinvestor.domain.Event;
import io.autoinvestor.domain.users.UserWasRegisteredEvent;
import io.autoinvestor.domain.users.UserWasRegisteredEventPayload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserCreatedEventMessageMapper {

    public List<UserCreatedMessage> map (List<Event<?>> userRegisteredEvent) {
        List<UserCreatedMessage> userCreatedMessages = new ArrayList<>();
        for (Event<?> userWasRegistered : userRegisteredEvent) {

            UserWasRegisteredEventPayload payload = (UserWasRegisteredEventPayload) userWasRegistered.getPayload();
            UserCreatedMessagePayload userCreatedMessagePayload = new UserCreatedMessagePayload(payload.firstName().value(), payload.lastName().value(), payload.email().value(), payload.riskLevel().value());
            UserCreatedMessage userCreatedMessage = new UserCreatedMessage(
                    userWasRegistered.getId().value(),
                    userWasRegistered.getOccurredAt(),
                    userWasRegistered.getAggregateId().value(),
                    userWasRegistered.getVersion(),
                    userWasRegistered.getType(),
                    userCreatedMessagePayload);
            userCreatedMessages.add(userCreatedMessage);
        }
        return userCreatedMessages;
    }
}
