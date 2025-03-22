package io.autoinvestor.infrastructure;

import io.autoinvestor.application.UserRegistredReadModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

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
