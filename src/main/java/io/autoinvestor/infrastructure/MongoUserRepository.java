package io.autoinvestor.infrastructure;

import io.autoinvestor.domain.Event;
import io.autoinvestor.domain.UserRepository;
import io.grpc.alts.internal.Identity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
@Primary
public class MongoUserRepository implements UserRepository {

    private final MongoTemplate template;
    private final CreateUserEventMapperDocument mapper;

    public MongoUserRepository(MongoTemplate template, CreateUserEventMapperDocument mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @Override
    public void save(List<Event<?>> userEvents) {
        List<UserCreatedEventDocument> documents = userEvents.stream()
                .map(mapper::toDocument)
                .collect(Collectors.toList());
        template.insertAll(documents);
    }
}
