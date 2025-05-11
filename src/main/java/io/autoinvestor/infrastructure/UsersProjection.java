package io.autoinvestor.infrastructure;

import io.autoinvestor.domain.users.UserWasRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UsersProjection {

    private final UserReadModelInMemory userReadModelInMemory;

    public UsersProjection(UserReadModelInMemory userReadModelInMemory) {
        this.userReadModelInMemory = userReadModelInMemory;
    }

    @EventListener
    public void onUserRegistered(UserWasRegisteredEvent userWasRegisteredEvent) {
        String userId = userWasRegisteredEvent.getAggregateId().value();
        String email = userWasRegisteredEvent.getPayload().email().value();
        String password = userWasRegisteredEvent.getPayload().userPassword().value();
        String firstName = userWasRegisteredEvent.getPayload().firstName().value();
        String lastName = userWasRegisteredEvent.getPayload().lastName().value();
        Integer riskLevel = userWasRegisteredEvent.getPayload().riskLevel().value();

        UserReadModelDocument userReadModelDocument = new UserReadModelDocument(userId, email, password, firstName,
                lastName, riskLevel);
        this.userReadModelInMemory.add(userReadModelDocument);
    }
}
