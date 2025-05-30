package io.autoinvestor.ui;

import io.autoinvestor.application.UpdateUserUseCase.UpdateUserCommand;
import io.autoinvestor.application.UpdateUserUseCase.UpdateUserCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UpdateUserController {

    private final UpdateUserCommandHandler updateUserCommandHandler;

    public ResponseEntity<Void> handle (
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestBody UpdateUserDTO dto
    ){
        updateUserCommandHandler.handle(new UpdateUserCommand(userId, dto.riskLevel()));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
