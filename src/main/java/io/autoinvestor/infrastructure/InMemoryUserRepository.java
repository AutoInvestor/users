package io.autoinvestor.infrastructure;

import io.autoinvestor.domain.UserRepository;
import io.autoinvestor.domain.users.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user);
    }

    public void clear() {
        users.clear();
    }
}
