package io.autoinvestor.ui;

import io.autoinvestor.application.RegisterUserCommand;
import io.autoinvestor.application.RegisterUserCommandHandler;
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
    public ResponseEntity<Void> handle(@RequestBody UserDTO dto) {
        commandHandler.handle(new RegisterUserCommand(dto.name()));
        return ResponseEntity.ok().build();
    }
}
