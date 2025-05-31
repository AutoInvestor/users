package io.autoinvestor.application.RequestUserUseCase;

import io.autoinvestor.application.UserDTO;
import io.autoinvestor.application.UserNotFound;
import io.autoinvestor.application.UsersReadModel;
import io.autoinvestor.ui.UserResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserQueryHandler {

    private final UsersReadModel usersReadModel;

    public UserResponse handle(GetUserQuery query) {
        UserDTO dto =
                this.usersReadModel
                        .get(query.email())
                        .orElseThrow(() -> UserNotFound.with(query.email()));

        return new UserResponse(
                dto.userId(), dto.firstName(), dto.lastName(), dto.email(), dto.riskLevel());
    }
}
