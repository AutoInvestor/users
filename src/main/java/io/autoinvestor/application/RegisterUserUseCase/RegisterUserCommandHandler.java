package io.autoinvestor.application.RegisterUserUseCase;

import io.autoinvestor.application.UserRegistredReadModel;
import io.autoinvestor.domain.Event;
import io.autoinvestor.domain.EventPublisher;
import io.autoinvestor.domain.UserRepository;
import io.autoinvestor.domain.users.User;
import java.util.List;

import io.autoinvestor.infrastructure.UserCreatedEventMessageMapper;
import io.autoinvestor.infrastructure.UserCreatedMessage;
import io.autoinvestor.infrastructure.UsersEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserCommandHandler {

    private final UserRepository repository;
    private final EventPublisher eventPublisher;
    private final UserRegistredReadModel userRegistredReadModel;
    private final UsersEventPublisher usersEventPublisher;
    private final UserCreatedEventMessageMapper userCreatedEventMessageMapper;
    public RegisterUserCommandHandler(UserRepository repository, EventPublisher eventPublisher,
            UserRegistredReadModel userRegistredReadModel, UsersEventPublisher usersEventPublisher,
                                      UserCreatedEventMessageMapper userCreatedEventMessageMapper) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.userRegistredReadModel = userRegistredReadModel;
        this.usersEventPublisher = usersEventPublisher;
        this.userCreatedEventMessageMapper = userCreatedEventMessageMapper;
    }

    public void handle(RegisterUserCommand command) {

        if (userRegistredReadModel.exists(command.email())) {
            throw UserRegisteredAlreadyExists.with(command.email());
        }

        User user = User.create(command.firstName(), command.lastName(), command.email(), command.password(), command.riskLevel());
        List<Event<?>> uncomittedEvents = user.releaseEvents();
        this.repository.save(uncomittedEvents);
        this.eventPublisher.publish(uncomittedEvents);
        List<UserCreatedMessage> userCreatedMessages = this.userCreatedEventMessageMapper.map(uncomittedEvents);
        this.usersEventPublisher.publishUserCreated(userCreatedMessages);
    }
}
