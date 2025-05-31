package io.autoinvestor.application.RegisterUserUseCase;

import io.autoinvestor.application.UserDTO;
import io.autoinvestor.application.UsersReadModel;
import io.autoinvestor.domain.EventStore;
import io.autoinvestor.domain.events.Event;
import io.autoinvestor.domain.events.EventPublisher;
import io.autoinvestor.domain.model.User;
import io.autoinvestor.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserCommandHandler {

    private final EventStore eventStore;
    private final EventPublisher eventPublisher;
    private final UsersReadModel readModel;

    public void handle(RegisterUserCommand command) {
        if (command.email() == null || command.email().isBlank()) {
            throw new BadRequestException("Email cannot be null or empty");
        }

        if (this.readModel.get(command.email()).isPresent()) {
            throw UserRegisteredAlreadyExists.with(command.email());
        }

        User user =
                User.create(
                        command.firstName(),
                        command.lastName(),
                        command.email(),
                        command.riskLevel());

        List<Event<?>> events = user.getUncommittedEvents();

        this.eventStore.save(user);

        UserDTO dto =
                new UserDTO(
                        user.getState().userId().value(),
                        command.email(),
                        command.firstName(),
                        command.lastName(),
                        command.riskLevel());
        this.readModel.save(dto);

        this.eventPublisher.publish(events);

        user.markEventsAsCommitted();
    }
}
