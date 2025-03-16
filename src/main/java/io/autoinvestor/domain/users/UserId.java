package io.autoinvestor.domain.users;

import io.autoinvestor.domain.Id;

public class UserId extends Id {
    UserId(String id) {
        super(id);
    }

    public static UserId generate() {
        return new UserId(generateId());
    }
}
