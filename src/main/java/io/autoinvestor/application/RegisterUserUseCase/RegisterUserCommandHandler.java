package io.autoinvestor.application.RegisterUserUseCase;

import io.autoinvestor.application.UsersReadModel;
import io.autoinvestor.domain.Event;
import io.autoinvestor.domain.EventPublisher;
import io.autoinvestor.domain.UserRepository;
import io.autoinvestor.domain.users.User;
import io.autoinvestor.exceptions.BadRequestException;
import io.autoinvestor.infrastructure.UserCreatedEventMessageMapper;
import io.autoinvestor.infrastructure.UserCreatedMessage;
import io.autoinvestor.infrastructure.UsersEventPublisher;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserCommandHandler {

    private final UserRepository repository;
    private final EventPublisher eventPublisher;
    private final UsersEventPublisher usersEventPublisher;
    private final UserCreatedEventMessageMapper userCreatedEventMessageMapper;
    private final UsersReadModel usersReadModel;

    public void handle(RegisterUserCommand command) {
        if (command.email() == null || command.email().isBlank()) {
            throw new BadRequestException("Email cannot be null or empty");
        }
        if (usersReadModel.get(command.email()) != null) {
            throw UserRegisteredAlreadyExists.with(command.email());
        }

        User user = User.create(command.firstName(), command.lastName(), command.email(), command.riskLevel());
        List<Event<?>> uncommittedEvents = user.releaseEvents();
        this.repository.save(uncommittedEvents);
        this.eventPublisher.publish(uncommittedEvents);
        List<UserCreatedMessage> userCreatedMessages = this.userCreatedEventMessageMapper.map(uncommittedEvents);
        this.usersEventPublisher.publishUserCreated(userCreatedMessages);
    }
}
