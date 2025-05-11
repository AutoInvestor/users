package io.autoinvestor.application.RegisterUserUseCase;

import io.autoinvestor.application.UsersReadModel;
import io.autoinvestor.domain.Event;
import io.autoinvestor.domain.EventPublisher;
import io.autoinvestor.domain.UserRepository;
import io.autoinvestor.domain.users.User;
import io.autoinvestor.infrastructure.UserCreatedEventMessageMapper;
import io.autoinvestor.infrastructure.UserCreatedMessage;
import io.autoinvestor.infrastructure.UsersEventPublisher;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserCommandHandler {

    private final UserRepository repository;
    private final EventPublisher eventPublisher;
    private final UsersEventPublisher usersEventPublisher;
    private final UserCreatedEventMessageMapper userCreatedEventMessageMapper;
    private UsersReadModel usersReadModel;

    public RegisterUserCommandHandler(UserRepository repository, EventPublisher eventPublisher,
            UsersEventPublisher usersEventPublisher, UserCreatedEventMessageMapper userCreatedEventMessageMapper,
            UsersReadModel usersReadModel) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.usersEventPublisher = usersEventPublisher;
        this.userCreatedEventMessageMapper = userCreatedEventMessageMapper;
        this.usersReadModel = usersReadModel;
    }

    public void handle(RegisterUserCommand command) {

        if (usersReadModel.get(command.email()) != null) {
            throw UserRegisteredAlreadyExists.with(command.email());
        }

        User user = User.create(command.firstName(), command.lastName(), command.email(), command.password(),
                command.riskLevel());
        List<Event<?>> uncomittedEvents = user.releaseEvents();
        this.repository.save(uncomittedEvents);
        this.eventPublisher.publish(uncomittedEvents);
        List<UserCreatedMessage> userCreatedMessages = this.userCreatedEventMessageMapper.map(uncomittedEvents);
        this.usersEventPublisher.publishUserCreated(userCreatedMessages);
    }
}
