package io.autoinvestor.infrastructure.read_models;

import io.autoinvestor.application.UserDTO;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDocument toDocument(UserDTO dto) {
        return new UserDocument(
                dto.userId(), dto.email(), dto.firstName(), dto.lastName(), dto.riskLevel());
    }

    public UserDTO toDTO(UserDocument doc) {
        return new UserDTO(
                doc.userId(), doc.email(), doc.firstName(), doc.lastName(), doc.riskLevel());
    }
}
