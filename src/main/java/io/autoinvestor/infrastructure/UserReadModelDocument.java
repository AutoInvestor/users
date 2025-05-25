package io.autoinvestor.infrastructure;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public record UserReadModelDocument(
        @Id String userId,
        String email,
        String firstName,
        String lastName,
        Integer riskLevel
) {
}
