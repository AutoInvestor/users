package io.autoinvestor.infrastructure;

public record UserReadModelDocument(String userId, String email, String password, String firstName, String lastName,
        Integer riskLevel) {
}
