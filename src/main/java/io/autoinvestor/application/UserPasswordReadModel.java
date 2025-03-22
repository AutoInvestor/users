package io.autoinvestor.application;

import io.autoinvestor.domain.users.User;

import java.util.Map;

public interface UserPasswordReadModel {
    public void save (String username, String password);
    public String getPassword (String username);
}
