package io.autoinvestor.application;

import io.autoinvestor.domain.EventPublisher;
import io.autoinvestor.domain.UserRepository;
import io.autoinvestor.domain.users.User;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserCommandHandler {

    private final UserRepository repository;
    private final EventPublisher eventPublisher;

    public RegisterUserCommandHandler(UserRepository repository, EventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void handle(RegisterUserCommand command) {
        User user = User.create(command.name());
        this.repository.save(user);
        this.eventPublisher.publish(user.releaseEvents());
    }
}
