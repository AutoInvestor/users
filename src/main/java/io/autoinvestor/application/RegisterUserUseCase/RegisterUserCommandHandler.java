package io.autoinvestor.application.RegisterUserUseCase;

import io.autoinvestor.application.UserRegistredReadModel;
import io.autoinvestor.domain.Event;
import io.autoinvestor.domain.EventPublisher;
import io.autoinvestor.domain.UserRepository;
import io.autoinvestor.domain.users.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserCommandHandler {

    private final UserRepository repository;
    private final EventPublisher eventPublisher;
    private final UserRegistredReadModel userRegistredReadModel;

    public RegisterUserCommandHandler(UserRepository repository, EventPublisher eventPublisher,
            UserRegistredReadModel userRegistredReadModel) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.userRegistredReadModel = userRegistredReadModel;
    }

    public void handle(RegisterUserCommand command) {

        if (userRegistredReadModel.exists(command.username())) {
            throw UserRegisteredAlreadyExists.with(command.username());
        }

        User user = User.create(command.username(), command.email(), command.password());
        List<Event<?>> uncomittedEvents = user.releaseEvents();
        this.repository.save(uncomittedEvents);
        this.eventPublisher.publish(uncomittedEvents);
    }
}
