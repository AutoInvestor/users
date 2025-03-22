package io.autoinvestor.application;

public interface UserPasswordReadModel {
    public void save(String username, String password);

    public String getPassword(String username);
}
