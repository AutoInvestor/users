package io.autoinvestor.application;

import io.autoinvestor.infrastructure.UserReadModelDocument;
import io.autoinvestor.ui.user.UserResponse;

public interface UsersReadModel {

    void add(UserReadModelDocument document);

    UserResponse get(String email);
}
