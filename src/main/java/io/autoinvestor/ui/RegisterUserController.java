package io.autoinvestor.ui;

import io.autoinvestor.application.RegisterUserUseCase.RegisterUserCommand;
import io.autoinvestor.application.RegisterUserUseCase.RegisterUserCommandHandler;
import io.autoinvestor.application.RequestUserById.GetUserByIdQueryHandler;
import io.autoinvestor.application.RequestUserById.GetUserByIdQuery;
import io.autoinvestor.application.RequestUserUseCase.GetUserQuery;
import io.autoinvestor.application.RequestUserUseCase.GetUserQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class RegisterUserController {

    private static final Integer DEFAULT_RISK_LEVEL = 1;

    private final RegisterUserCommandHandler registerUserCommandHandler;

    @PostMapping
    public ResponseEntity<Void> handle(@RequestBody RegisterUserRequest dto) {
        registerUserCommandHandler.handle(new RegisterUserCommand(
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.riskLevel() != null ? dto.riskLevel() : DEFAULT_RISK_LEVEL
        ));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
