package io.autoinvestor.application.RequestUserById;

import io.autoinvestor.application.UsersReadModel;
import io.autoinvestor.ui.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserByIdCommandHandler {
    private final UsersReadModel readModel;

    public UserResponse handle (GetUserByIdQuery query) {
        return readModel.getById(query.userId());
    }
}
