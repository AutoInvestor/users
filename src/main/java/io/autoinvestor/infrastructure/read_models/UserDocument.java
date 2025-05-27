package io.autoinvestor.infrastructure.read_models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public record UserDocument(
        @Id String userId,
        String email,
        String firstName,
        String lastName,
        Integer riskLevel
) {
}
