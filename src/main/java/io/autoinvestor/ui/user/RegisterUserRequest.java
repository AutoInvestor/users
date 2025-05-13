package io.autoinvestor.ui.user;

public record RegisterUserRequest(
        String firstName,
        String lastName,
        String email,
        Integer riskLevel
) {
}
