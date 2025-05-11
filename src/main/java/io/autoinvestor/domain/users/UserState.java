package io.autoinvestor.domain.users;

import java.util.Date;

public class UserState {
    private UserId userId;
    private FirstName firstName;
    private LastName lastName;
    private UserEmail userEmail;
    private Date createdAt;
    private Date updatedAt;
    private UserPassword userPassword;

    public UserState(UserId userId, FirstName firstName, LastName lastName, UserEmail userEmail, Date createdAt,
            Date updatedAt, UserPassword userPassword) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userPassword = userPassword;
    }

    public static UserState withUserCreated(UserWasRegisteredEvent event) {
        UserWasRegisteredEventPayload payload = event.getPayload();
        return new UserState((UserId) event.getAggregateId(), payload.firstName(), payload.lastName(), payload.email(),
                new Date(), new Date(), payload.userPassword());
    }
}
