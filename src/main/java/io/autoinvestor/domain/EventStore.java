package io.autoinvestor.domain;

import io.autoinvestor.domain.model.User;
import io.autoinvestor.domain.model.UserId;


public interface EventStore {
    void save(User user);
    User get(UserId userId);
}
