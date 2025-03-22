package io.autoinvestor.domain.users;

import io.autoinvestor.domain.AggregateRoot;
import io.autoinvestor.domain.Event;
import java.util.ArrayList;
import java.util.List;

public class User extends AggregateRoot {
    private UserState userState;

    private User(List<Event<?>> stream) {
        super(stream);
    }

    @Override
    protected void when(Event<?> event) {
        switch (event.getType()) {
            case "UserWasRegisteredEvent" :
                this.whenUserCreated((UserWasRegisteredEvent) event);
                break;
        }
    }

    public static User empty() {
        return new User(new ArrayList<>());
    }

    private void whenUserCreated(UserWasRegisteredEvent event) {
        this.userState = UserState.withUserCreated(event);
    }

    public static User create(String name, String email, String rawPassword) {
        UserId id = UserId.generate();
        UserName username = new UserName(name);
        UserEmail userEmail = new UserEmail(email);
        UserPassword encryptedPassword = UserPassword.create(rawPassword);
        User user = User.empty();
        user.createUser(id, username, userEmail, encryptedPassword);
        return user;
    }

    public void createUser(UserId userId, UserName userName, UserEmail userEmail, UserPassword userPassword) {
        this.apply(UserWasRegisteredEvent.with(userId, userName, userEmail, userPassword));
    }
}
