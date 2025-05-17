package io.autoinvestor.infrastructure;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document(collection = "userCreated")
public record UserCreatedEventDocument(
        @Id String id,
        String aggregateId,
        String firstName,
        String lastName,
        String email,
        Integer riskLevel,
        @Field("occurredAt") Instant occurredAt,
        Integer version

) {}
