package io.autoinvestor.application.RegisterUserUseCase;

public record RegisterUserCommand(String username, String email, String password) {
}
