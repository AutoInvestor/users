package io.autoinvestor.infrastructure;

import io.autoinvestor.application.UserPasswordReadModel;
import io.autoinvestor.application.UserRegistredReadModel;
import io.autoinvestor.domain.users.UserWasRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UsersProjection {

    private final UserRegistredReadModel userRegisteredReadModel;
    private final UserPasswordReadModel userPasswordReadModel;

    public UsersProjection(UserRegisteredReadModel userRegisteredReadModel,
            UserPasswordReadModel userPasswordReadModel) {
        this.userRegisteredReadModel = userRegisteredReadModel;
        this.userPasswordReadModel = userPasswordReadModel;
    }

    @EventListener
    public void onUserRegistered(UserWasRegisteredEvent userWasRegisteredEvent) {
        this.userRegisteredReadModel.add(userWasRegisteredEvent.getPayload().username().value());
        this.userPasswordReadModel.save(userWasRegisteredEvent.getPayload().username().value(),
                userWasRegisteredEvent.getPayload().userPassword().value());
    }
}
