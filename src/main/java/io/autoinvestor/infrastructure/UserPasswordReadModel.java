package io.autoinvestor.infrastructure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository

public class UserPasswordReadModel implements io.autoinvestor.application.UserPasswordReadModel {

    private Map<String, String> userAndPassword = new HashMap<>();

    @Override
    public void save(String username, String password) {
        userAndPassword.put(username, password);
    }

    @Override
    public String getPassword(String username) {
        return userAndPassword.get(username);
    }
}
