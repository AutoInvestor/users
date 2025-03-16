package io.autoinvestor.domain.users;

import io.autoinvestor.domain.AggregateRoot;
import java.util.Date;

public class User extends AggregateRoot {
    private final UserId id;
    private final UserName name;
    private final Date createdAt;
    private Date updatedAt;

    private User(UserId id, UserName name, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User create(String name) {
        UserId id = UserId.generate();
        UserName username = new UserName(name);
        Date createdAt = new Date();
        Date updatedAt = new Date();

        User user = new User(id, username, createdAt, updatedAt);
        user.recordEvent(UserWasRegisteredEvent.from(user));
        return user;
    }

    public UserId getId() {
        return id;
    }

    public UserName getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
