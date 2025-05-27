package io.autoinvestor.domain.model;

import io.autoinvestor.domain.events.Event;
import io.autoinvestor.domain.events.EventSourcedEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User extends EventSourcedEntity {

    private UserState state;

    private User(List<Event<?>> stream) {
        super(stream);

        if (stream.isEmpty()) {
            this.state = UserState.empty();
        }
    }

    public static User empty() {
        return new User(new ArrayList<>());
    }

    public static User from(List<Event<?>> stream) {
        return new User(stream);
    }

    public static User create(String firstName, String lastName, String email, int riskLevel) {
        User user = User.empty();

        user.apply(UserWasRegisteredEvent.with(
                user.getState().userId(),
                FirstName.from(firstName),
                LastName.from(lastName),
                UserEmail.from(email),
                RiskLevel.from(riskLevel),
                user.version
        ));

        return user;
    }

    @Override
    protected void when(Event<?> event) {
        switch (event.getType()) {
            case UserWasRegisteredEvent.TYPE :
                whenUserCreated((UserWasRegisteredEvent) event);
                break;
            default:
                throw new IllegalArgumentException("Unknown event type");
        }
    }

    private void whenUserCreated(UserWasRegisteredEvent event) {
        if (this.state != null) {
            this.state = UserState.empty();
        }
        assert this.state != null;
        this.state = this.state.withUserCreated(event);
    }
}
