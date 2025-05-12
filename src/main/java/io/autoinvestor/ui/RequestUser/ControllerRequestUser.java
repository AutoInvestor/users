package io.autoinvestor.ui.RequestUser;

import io.autoinvestor.application.RequestUserUseCase.GetUserQuery;
import io.autoinvestor.application.RequestUserUseCase.GetUserQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ControllerRequestUser {

    private GetUserQueryHandler getUserCommandHandler;

    public ControllerRequestUser(GetUserQueryHandler getUserCommandHandler) {
        this.getUserCommandHandler = getUserCommandHandler;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUser(@RequestParam String email) {
        return ResponseEntity.ok(getUserCommandHandler.handle(new GetUserQuery(email)));
    }
}
