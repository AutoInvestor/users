package io.autoinvestor.ui.RegisterUser;

import io.autoinvestor.application.RegisterUserUseCase.RegisterUserCommand;
import io.autoinvestor.application.RegisterUserUseCase.RegisterUserCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registerUser")
public class RegisterUserController {

    private final RegisterUserCommandHandler commandHandler;

    public RegisterUserController(RegisterUserCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public ResponseEntity<Void> handle(@RequestBody RegisterUserDTO dto) {

        commandHandler.handle(new RegisterUserCommand(dto.username(), dto.email(), dto.password()));
        return ResponseEntity.ok().build();
    }
}
