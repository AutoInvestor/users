package io.autoinvestor.application;

public record UserDTO(String userId,
                      String email,
                      String firstName,
                      String lastName,
                      int riskLevel) {
}
