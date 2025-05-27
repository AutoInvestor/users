package io.autoinvestor.infrastructure.repositories;

import io.autoinvestor.domain.events.Event;
import io.autoinvestor.domain.EventStore;
import io.autoinvestor.domain.model.User;
import io.autoinvestor.domain.model.UserId;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
@Primary
@Profile("prod")
public class MongoEventStoreRepository implements EventStore {
    private static final String COLLECTION = "events";

    private final MongoTemplate template;
    private final EventMapper mapper;

    public MongoEventStoreRepository(MongoTemplate template, EventMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        List<EventDocument> docs = user.getUncommittedEvents()
                .stream()
                .map(mapper::toDocument)
                .collect(Collectors.toList());
        template.insertAll(docs);
    }

    @Override
    public User get(UserId userId) {
        Query q = Query.query(
                        Criteria.where("aggregateId")
                                .is(userId.value())
                )
                .with(Sort.by("version"));

        List<EventDocument> docs = template.find(q, EventDocument.class, COLLECTION);

        if (docs.isEmpty()) {
            return null;
        }

        List<Event<?>> events = docs.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());

        return User.from(events);
    }
}
