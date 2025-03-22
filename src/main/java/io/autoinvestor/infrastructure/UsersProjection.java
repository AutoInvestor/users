package io.autoinvestor.infrastructure;

import io.autoinvestor.domain.users.UserWasRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UsersProjection {
    private final UserRegisteredReadModel userRegisteredReadModel;

    public UsersProjection (UserRegisteredReadModel userRegisteredReadModel) {
        this.userRegisteredReadModel = userRegisteredReadModel;
    }
    @EventListener
    public void onUserRegistered(UserWasRegisteredEvent userWasRegisteredEvent) {
        this.userRegisteredReadModel.add(userWasRegisteredEvent.getPayload().username().value());
    }
}
