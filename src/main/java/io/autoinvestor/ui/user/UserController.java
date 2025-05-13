package io.autoinvestor.ui.user;

import io.autoinvestor.application.RegisterUserUseCase.RegisterUserCommand;
import io.autoinvestor.application.RegisterUserUseCase.RegisterUserCommandHandler;
import io.autoinvestor.application.RequestUserUseCase.GetUserQuery;
import io.autoinvestor.application.RequestUserUseCase.GetUserQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final GetUserQueryHandler getUserCommandHandler;
    private final RegisterUserCommandHandler registerUserCommandHandler;

    @GetMapping
    public ResponseEntity<UserResponse> getUser(@RequestParam("email") String email) {
        return ResponseEntity.ok(getUserCommandHandler.handle(new GetUserQuery(email)));
    }

    @PostMapping
    public ResponseEntity<Void> handle(@RequestBody RegisterUserRequest dto) {
        registerUserCommandHandler.handle(new RegisterUserCommand(dto.firstName(), dto.lastName(), dto.email(), dto.riskLevel()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
