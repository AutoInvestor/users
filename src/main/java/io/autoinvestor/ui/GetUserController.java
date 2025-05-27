package io.autoinvestor.ui;

import io.autoinvestor.application.RequestUserById.GetUserByIdQueryHandler;
import io.autoinvestor.application.RequestUserById.GetUserByIdQuery;
import io.autoinvestor.application.RequestUserUseCase.GetUserQuery;
import io.autoinvestor.application.RequestUserUseCase.GetUserQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class GetUserController {

    private final GetUserQueryHandler getUserCommandHandler;
    private final GetUserByIdQueryHandler getUserByIdQueryHandler;

    @GetMapping
    public ResponseEntity<UserResponse> getUser(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestParam(value = "email", required = false) String email
    ) {
        if (userId != null) {
            return ResponseEntity.ok(getUserByIdQueryHandler.handle(new GetUserByIdQuery(userId)));
        } if (email != null) {
            return ResponseEntity.ok(getUserCommandHandler.handle(new GetUserQuery(email)));
        }
        return ResponseEntity.badRequest().build();
    }
}
