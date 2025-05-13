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

    private static final Integer DEFAULT_RISK_LEVEL = 1;

    private final GetUserQueryHandler getUserCommandHandler;
    private final RegisterUserCommandHandler registerUserCommandHandler;

    @GetMapping
    public ResponseEntity<UserResponse> getUser(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestParam(value = "email", required = false) String email
    ) {
        if (userId != null) {
            // TODO: return user by userId
            return ResponseEntity.ok(new UserResponse(userId, null, null, null, null));
        } if (email != null) {
            return ResponseEntity.ok(getUserCommandHandler.handle(new GetUserQuery(email)));
        }
        return ResponseEntity.badRequest().build();
    }

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
