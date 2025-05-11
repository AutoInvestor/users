package io.autoinvestor.application.RequestUserUseCase;

import io.autoinvestor.application.UsersReadModel;
import io.autoinvestor.ui.RequestUser.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class GetUserQueryHandler {

    private UsersReadModel usersReadModel;

    public GetUserQueryHandler(UsersReadModel usersReadModel) {
        this.usersReadModel = usersReadModel;
    }

    public UserResponse handle(GetUserQuery getUserQuery) {
        if (usersReadModel.get(getUserQuery.email()) == null) {
            throw UserNotFound.with(getUserQuery.email());
        }
        return usersReadModel.get(getUserQuery.email());
    }
}
