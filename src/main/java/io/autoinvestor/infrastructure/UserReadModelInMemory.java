package io.autoinvestor.infrastructure;

import io.autoinvestor.application.UsersReadModel;
import io.autoinvestor.ui.user.UserResponse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserReadModelInMemory implements UsersReadModel {

    private final List<UserReadModelDocument> userReadModelDocuments;

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
