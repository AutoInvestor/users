package io.autoinvestor.application.RegisterUserUseCase;

public record RegisterUserCommand(String firstName, String lastName, String email, String password, Integer riskLevel) {
}
