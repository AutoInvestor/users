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

    public static User create(String firstName, String lastName, String email, String rawPassword, Integer riskLevel) {
        UserId id = UserId.generate();
        FirstName firstNameDTO = new FirstName(firstName);
        LastName lastNameDTO = new LastName(lastName);
        RiskLevel riskLevelDTO = new RiskLevel(riskLevel);
        UserEmail userEmail = new UserEmail(email);
        UserPassword encryptedPassword = UserPassword.create(rawPassword);
        User user = User.empty();
        user.createUser(id, firstNameDTO, lastNameDTO, userEmail, encryptedPassword, riskLevelDTO);
        return user;
    }

    public void createUser(UserId userId, FirstName firstName, LastName lastName, UserEmail userEmail,
            UserPassword userPassword, RiskLevel riskLevel) {
        this.apply(UserWasRegisteredEvent.with(userId, firstName, lastName, userEmail, userPassword, riskLevel));
    }
}
