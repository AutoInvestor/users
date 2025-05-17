package io.autoinvestor.infrastructure;

import io.autoinvestor.domain.users.UserWasRegisteredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class UsersProjection {

    private final UserReadModelInMemory userReadModelInMemory;
    private final UserReadModelMongo userReadModelMongo;

    public UsersProjection(UserReadModelInMemory userReadModelInMemory, UserReadModelMongo userReadModelMongo) {
        this.userReadModelInMemory = userReadModelInMemory;
        this.userReadModelMongo = userReadModelMongo;
    }

    @EventListener
    public void onUserRegistered(UserWasRegisteredEvent userWasRegisteredEvent) {
        String userId = userWasRegisteredEvent.getAggregateId().value();
        String email = userWasRegisteredEvent.getPayload().email().value();
        String firstName = userWasRegisteredEvent.getPayload().firstName().value();
        String lastName = userWasRegisteredEvent.getPayload().lastName().value();
        Integer riskLevel = userWasRegisteredEvent.getPayload().riskLevel().value();

        UserReadModelDocument userReadModelDocument = new UserReadModelDocument(userId, email, firstName, lastName, riskLevel);
        //this.userReadModelInMemory.add(userReadModelDocument);
        this.userReadModelMongo.add(userReadModelDocument);
    }
}
