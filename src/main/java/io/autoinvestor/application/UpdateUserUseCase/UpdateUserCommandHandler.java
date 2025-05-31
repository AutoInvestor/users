package io.autoinvestor.application.UpdateUserUseCase;

import io.autoinvestor.application.UserDTO;
import io.autoinvestor.application.UserNotFound;
import io.autoinvestor.application.UsersReadModel;
import io.autoinvestor.domain.EventStore;
import io.autoinvestor.domain.events.Event;
import io.autoinvestor.domain.events.EventPublisher;
import io.autoinvestor.domain.model.User;
import io.autoinvestor.domain.model.UserId;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserCommandHandler {

    private final EventStore eventStore;
    private final EventPublisher eventPublisher;
    private final UsersReadModel readModel;

    public void handle(UpdateUserCommand command) {

        String userIdToUpdate =
                String.valueOf(
                        readModel
                                .getById(command.userId())
                                .map(UserDTO::riskLevel)
                                .orElseThrow(() -> UserNotFound.with(command.userId())));
        User user = this.eventStore.get(UserId.from(userIdToUpdate));
        user.update(userIdToUpdate, command.riskLevel());

        List<Event<?>> events = user.getUncommittedEvents();

        this.eventStore.save(user);

        UserDTO dto =
                new UserDTO(
                        user.getState().userId().value(),
                        user.getState().userEmail().value(),
                        user.getState().firstName().value(),
                        user.getState().lastName().value(),
                        command.riskLevel());
        this.readModel.update(dto);

        this.eventPublisher.publish(events);

        user.markEventsAsCommitted();
    }
}
