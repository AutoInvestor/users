package io.autoinvestor.application;

public interface UserRegistredReadModel {
    public boolean exists(String userId);

    public void add(String userId);
}
