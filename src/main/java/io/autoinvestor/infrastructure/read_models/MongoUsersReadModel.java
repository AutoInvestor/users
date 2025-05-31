package io.autoinvestor.infrastructure.read_models;

import io.autoinvestor.application.UserDTO;
import io.autoinvestor.application.UsersReadModel;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@Primary
@Profile("prod")
public class MongoUsersReadModel implements UsersReadModel {

    private static final String COLLECTION = "users";

    private final MongoTemplate template;
    private final UserMapper mapper;

    public MongoUsersReadModel(MongoTemplate template, UserMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @Override
    public void save(UserDTO dto) {
        template.save(mapper.toDocument(dto), COLLECTION);
    }

    @Override
    public Optional<UserDTO> get(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        UserDocument doc = template.findOne(query, UserDocument.class, COLLECTION);
        return Optional.ofNullable(doc).map(mapper::toDTO);
    }

    @Override
    public Optional<UserDTO> getById(String userId) {
        Query query = new Query(Criteria.where("_id").is(userId));
        UserDocument doc = template.findOne(query, UserDocument.class, COLLECTION);
        return Optional.ofNullable(doc).map(mapper::toDTO);
    }

    @Override
    public void update(UserDTO dto) {
        Query query = new Query(Criteria.where("userId").is(dto.userId()));
        Update update = new Update().set("riskLevel", dto.riskLevel());
        this.template.updateFirst(query, update, UserDocument.class, COLLECTION);
    }
}
