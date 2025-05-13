package io.autoinvestor.application.RegisterUserUseCase;

import jakarta.annotation.Nullable;

public record RegisterUserCommand(
        @Nullable String firstName,
        @Nullable String lastName,
        String email,
        Integer riskLevel
) {
}
