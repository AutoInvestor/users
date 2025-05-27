package io.autoinvestor.ui;

public record RegisterUserRequest(
        String firstName,
        String lastName,
        String email,
        Integer riskLevel
) {
}
