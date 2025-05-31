package io.autoinvestor.infrastructure.read_models;

import io.autoinvestor.application.UserDTO;
import io.autoinvestor.application.UsersReadModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUsersReadModel implements UsersReadModel {

    private final List<UserDTO> users = new ArrayList<>();

    @Override
    public void save(UserDTO dto) {
        users.add(dto);
    }

    @Override
    public Optional<UserDTO> get(String email) {
        return users.stream().filter(user -> user.email().equals(email)).findFirst();
    }

    @Override
    public Optional<UserDTO> getById(String userId) {
        return users.stream().filter(user -> user.userId().equals(userId)).findFirst();
    }

    @Override
    public void update(UserDTO dto) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userId().equals(dto.userId())) {
                users.set(i, dto);
                return;
            }
        }
    }
}
