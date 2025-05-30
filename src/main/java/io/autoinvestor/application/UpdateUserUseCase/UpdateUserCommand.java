package io.autoinvestor.application.UpdateUserUseCase;

public record UpdateUserCommand(
        String userId,
        int riskLevel
) {
}
