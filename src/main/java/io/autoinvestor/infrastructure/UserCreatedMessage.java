package io.autoinvestor.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public record UserCreatedMessage(@JsonProperty("eventId") String eventId, @JsonProperty("occurredAt") Date occurredAt,
        @JsonProperty("aggregateId") String aggregateId, @JsonProperty("version") int version,
        @JsonProperty("type") String type, @JsonProperty("payload") UserCreatedMessagePayload payload) {
    public record Payload(@JsonProperty("email") String email, @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName, @JsonProperty("riskLevel") int riskLevel) {
    }
}
