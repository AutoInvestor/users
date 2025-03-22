package io.autoinvestor.application.LoginUseCase;

import io.autoinvestor.application.PasswordService;
import io.autoinvestor.application.UserPasswordReadModel;

public class LoginCommandHandler {
    private UserPasswordReadModel userPasswordReadModel;

    private LoginCommandHandler(UserPasswordReadModel userPasswordReadModel) {
        this.userPasswordReadModel = userPasswordReadModel;
    }

    public void handle(LoginCommand loginCommand) {
        if (!PasswordService.matches(loginCommand.password(),
                userPasswordReadModel.getPassword(loginCommand.username()))) {
            throw UnauthorizedPassword.with();
        }
    }
}
