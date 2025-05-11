package io.autoinvestor.infrastructure;

import io.autoinvestor.application.UsersReadModel;
import io.autoinvestor.ui.RequestUser.UserResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserReadModelInMemory implements UsersReadModel {
    private List<UserReadModelDocument> userReadModelDocuments;

    public UserReadModelInMemory() {
        userReadModelDocuments = new ArrayList<>();
    }

    public void add(UserReadModelDocument document) {
        userReadModelDocuments.add(document);
    }

    @Override
    public UserResponse get(String email) {
        UserReadModelDocument userReadModelDocument = userReadModelDocuments.stream()
                .filter(document -> document.email().equals(email)).findFirst().orElse(null);
        if (userReadModelDocument == null) {
            return null;
        } else {
            return UserResponseDocumentMapper.map(userReadModelDocument);
        }
    }
}
