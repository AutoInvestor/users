package io.autoinvestor.ui.user;

public record UserResponse(
        String userId,
        String email,
        String firstName,
        String lastName,
        Integer riskLevel
) {
}
