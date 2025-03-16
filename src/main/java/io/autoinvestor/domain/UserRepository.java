package io.autoinvestor.domain;

import io.autoinvestor.domain.users.User;

public interface UserRepository {
    void save(User user);
}
