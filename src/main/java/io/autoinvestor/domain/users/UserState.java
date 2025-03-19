package io.autoinvestor.domain.users;

import java.util.Date;

public class UserState {
    private UserId userId;
    private UserName userName;
    private UserEmail userEmail;
    private Date createdAt;
    private Date updatedAt;

    public UserState(UserId userId, UserName userName, UserEmail userEmail, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static UserState withUserCreated (UserWasRegisteredEvent event) {
        UserWasRegisteredEventPayload payload = event.getPayload();
        return new UserState((UserId) event.getAggregateId(), payload.name(), payload.email(), new Date(), new Date());
    }
}
