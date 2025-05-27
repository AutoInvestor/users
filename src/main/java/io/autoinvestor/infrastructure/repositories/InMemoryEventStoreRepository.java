package io.autoinvestor.infrastructure.repositories;

import io.autoinvestor.domain.events.Event;
import io.autoinvestor.domain.EventStore;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.autoinvestor.domain.model.User;
import io.autoinvestor.domain.model.UserId;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@Profile("local")
public class InMemoryEventStoreRepository implements EventStore {
    private final List<Event<?>> eventStore = new ArrayList<>();

    @Override
    public void save(User user) {
        List<Event<?>> uncommittedEvents = user.getUncommittedEvents();
        if (!uncommittedEvents.isEmpty()) {
            eventStore.addAll(uncommittedEvents);
            user.markEventsAsCommitted();
        }
    }

    @Override
    public User get(UserId userId) {
        if (eventStore.isEmpty()) {
            return null;
        }

        List<Event<?>> userEvents = eventStore.stream()
                .filter(event -> event.getAggregateId().value().equals(userId.value()))
                .toList();

        return User.from(userEvents);
    }
}
