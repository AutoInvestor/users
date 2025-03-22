package io.autoinvestor.domain;

import io.autoinvestor.domain.users.User;

import java.util.List;

public interface UserRepository {
    void save(List<Event<?>> userEvents);
}
