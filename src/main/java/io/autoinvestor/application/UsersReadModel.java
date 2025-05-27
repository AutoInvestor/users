package io.autoinvestor.application;


import java.util.Optional;

public interface UsersReadModel {
    void save(UserDTO user);
    Optional<UserDTO> get(String email);
    Optional<UserDTO> getById(String userId);
}
