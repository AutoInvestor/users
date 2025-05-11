package io.autoinvestor.application;

import io.autoinvestor.infrastructure.UserReadModelDocument;
import io.autoinvestor.ui.RequestUser.UserResponse;

public interface UsersReadModel {
    public void add(UserReadModelDocument document);

    public UserResponse get(String email);
}
