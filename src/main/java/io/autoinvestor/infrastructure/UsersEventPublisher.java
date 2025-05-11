package io.autoinvestor.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import io.autoinvestor.domain.Event;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UsersEventPublisher {

    private static final String TOPIC = "users";
    private final PubSubTemplate pubSubTemplate;

    public UsersEventPublisher(PubSubTemplate pubSubTemplate) {
        this.pubSubTemplate = pubSubTemplate;
    }

    public void publishUserCreated(List<UserCreatedMessage> userCreatedMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        for (UserCreatedMessage message : userCreatedMessage) {

            try {
                String jsonMessage = objectMapper.writeValueAsString(message);
                pubSubTemplate.publish(TOPIC, jsonMessage);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error serializing message to JSON", e);
            }
        }
    }

}
