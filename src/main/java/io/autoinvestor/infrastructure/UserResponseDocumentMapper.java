package io.autoinvestor.infrastructure;

import io.autoinvestor.ui.user.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserResponseDocumentMapper {

    public static UserResponse map(UserReadModelDocument document) {
        return new UserResponse(document.email(), document.firstName(), document.lastName(), document.riskLevel());
    }
}
