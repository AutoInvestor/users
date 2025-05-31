package io.autoinvestor.ui;

public record UserResponse(
        String userId, String email, String firstName, String lastName, int riskLevel) {}
