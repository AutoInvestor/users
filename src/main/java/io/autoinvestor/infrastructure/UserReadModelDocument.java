package io.autoinvestor.infrastructure;

public record UserReadModelDocument(
        String userId,
        String email,
        String firstName,
        String lastName,
        Integer riskLevel
) {
}
