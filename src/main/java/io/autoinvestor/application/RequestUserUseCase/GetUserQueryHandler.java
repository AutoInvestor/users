package io.autoinvestor.application.RequestUserUseCase;

import io.autoinvestor.application.UsersReadModel;
import io.autoinvestor.ui.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserQueryHandler {

    private final UsersReadModel usersReadModel;

    public UserResponse handle(GetUserQuery getUserQuery) {
        if (usersReadModel.get(getUserQuery.email()) == null) {
            throw UserNotFound.with(getUserQuery.email());
        }
        return usersReadModel.get(getUserQuery.email());
    }
}
