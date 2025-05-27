package io.autoinvestor.application.RequestUserById;

import io.autoinvestor.application.UserNotFound;
import io.autoinvestor.application.UserDTO;
import io.autoinvestor.application.UsersReadModel;
import io.autoinvestor.ui.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdQueryHandler {
    private final UsersReadModel readModel;

    public UserResponse handle (GetUserByIdQuery query) {
        UserDTO dto = this.readModel.getById(query.userId())
                .orElseThrow(() -> UserNotFound.with(query.userId()));

        return new UserResponse(
                dto.userId(),
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.riskLevel()
        );
    }
}
