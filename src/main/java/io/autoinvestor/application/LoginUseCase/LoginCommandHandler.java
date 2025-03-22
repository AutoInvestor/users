package io.autoinvestor.application.LoginUseCase;

import io.autoinvestor.application.PasswordService;
import io.autoinvestor.application.UserPasswordReadModel;
import io.autoinvestor.application.UserRegistredReadModel;
import org.springframework.stereotype.Component;

@Component
public class LoginCommandHandler {
    private UserPasswordReadModel userPasswordReadModel;
    private UserRegistredReadModel userRegistredReadModel;

    private LoginCommandHandler(UserPasswordReadModel userPasswordReadModel,
            UserRegistredReadModel userRegistredReadModel) {
        this.userPasswordReadModel = userPasswordReadModel;
        this.userRegistredReadModel = userRegistredReadModel;
    }

    public void handle(LoginCommand loginCommand) {

        if (!userRegistredReadModel.exists(loginCommand.username())) {
            throw LoginUserNotFound.with(loginCommand.username());
        }

        if (!PasswordService.matches(loginCommand.password(),
                userPasswordReadModel.getPassword(loginCommand.username()))) {
            throw UnauthorizedPassword.with();
        }
    }
}
