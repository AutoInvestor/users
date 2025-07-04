package io.autoinvestor.infrastructure.repositories;

import io.autoinvestor.domain.events.Event;
import io.autoinvestor.domain.events.EventId;
import io.autoinvestor.domain.events.EventPayload;
import io.autoinvestor.domain.model.*;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EventMapper {

    private final ObjectMapper json = new ObjectMapper();

    public <P extends EventPayload> EventDocument toDocument(Event<P> evt) {
        Map<String, Object> payloadMap =
                json.convertValue(evt.getPayload(), new TypeReference<Map<String, Object>>() {});

        return new EventDocument(
                evt.getId().value(),
                evt.getAggregateId().value(),
                evt.getType(),
                payloadMap,
                evt.getOccurredAt(),
                evt.getVersion());
    }

    public Event<?> toDomain(EventDocument doc) {
        EventId id = EventId.from(doc.getId());
        UserId aggId = UserId.from(doc.getAggregateId());
        Date occurred = doc.getOccurredAt();
        int version = doc.getVersion();

        switch (doc.getType()) {
            case UserWasRegisteredEvent.TYPE -> {
                UserWasRegisteredEventPayload payload =
                        json.convertValue(doc.getPayload(), UserWasRegisteredEventPayload.class);

                return UserWasRegisteredEvent.hydrate(id, aggId, payload, occurred, version);
            }
            case UserWasUpdatedEvent.TYPE -> {
                UserWasUpdatedEventPayload payload =
                        json.convertValue(doc.getPayload(), UserWasUpdatedEventPayload.class);
                return UserWasUpdatedEvent.hydrate(id, aggId, payload, occurred, version);
            }

            default -> throw new IllegalArgumentException("Unknown event type: " + doc.getType());
        }
    }
}
