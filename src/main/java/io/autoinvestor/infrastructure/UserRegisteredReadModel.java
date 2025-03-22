package io.autoinvestor.infrastructure;

import io.autoinvestor.application.UserRegistredReadModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRegisteredReadModel implements UserRegistredReadModel {
    private final List<String> usersRegistered = new ArrayList<>();

    public boolean exists(String username) {
        return usersRegistered.contains(username);
    }

    public void add(String userId) {
        this.usersRegistered.add(userId);
    }
}
