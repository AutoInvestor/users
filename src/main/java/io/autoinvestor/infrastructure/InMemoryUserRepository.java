package io.autoinvestor.infrastructure;

import io.autoinvestor.domain.Event;
import io.autoinvestor.domain.UserRepository;
import io.autoinvestor.domain.users.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final List<Event<?>> eventStore = new ArrayList<>();

    @Override
    public void save(List<Event<?>> userEvents) {
        eventStore.addAll(userEvents);
    }


}
