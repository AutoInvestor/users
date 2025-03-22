package io.autoinvestor.domain.users;

import java.util.Date;

public class UserState {
    private UserId userId;
    private UserName userName;
    private UserEmail userEmail;
    private Date createdAt;
    private Date updatedAt;
    private UserPassword userPassword;

    public UserState(UserId userId, UserName userName, UserEmail userEmail, Date createdAt, Date updatedAt,
            UserPassword userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userPassword = userPassword;
    }

    public static UserState withUserCreated(UserWasRegisteredEvent event) {
        UserWasRegisteredEventPayload payload = event.getPayload();
        return new UserState((UserId) event.getAggregateId(), payload.username(), payload.email(), new Date(),
                new Date(), payload.userPassword());
    }
}
