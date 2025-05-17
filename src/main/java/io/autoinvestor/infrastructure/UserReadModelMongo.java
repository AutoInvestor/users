package io.autoinvestor.infrastructure;

import io.autoinvestor.application.UsersReadModel;
import io.autoinvestor.ui.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class UserReadModelMongo implements UsersReadModel {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(UserReadModelDocument document) {
        mongoTemplate.save(document);
    }

    @Override
    public UserResponse get(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        UserReadModelDocument document = mongoTemplate.findOne(query, UserReadModelDocument.class);
        if (document == null) {
            return null;
        }
        return UserResponseDocumentMapper.map(document);
    }
}
