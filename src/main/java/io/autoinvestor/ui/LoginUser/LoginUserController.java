package io.autoinvestor.ui.LoginUser;

import io.autoinvestor.application.LoginUseCase.LoginCommand;
import io.autoinvestor.application.LoginUseCase.LoginCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loginUser")
public class LoginUserController {

    private final LoginCommandHandler loginCommandHandler;

    public LoginUserController(LoginCommandHandler loginCommandHandler) {
        this.loginCommandHandler = loginCommandHandler;
    }

    @PostMapping
    public ResponseEntity<Void> handle(@RequestBody LoginUserDTO dto) {
        loginCommandHandler.handle(new LoginCommand(dto.username(), dto.password()));
        return ResponseEntity.ok().build();
    }
}
