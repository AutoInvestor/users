package io.autoinvestor.infrastructure;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.springframework.stereotype.Service;

@Service
public class UsersEventPublisher {

    private static final String TOPIC = "users";
    private final PubSubTemplate pubSubTemplate;

    public UsersEventPublisher(PubSubTemplate pubSubTemplate) {
        this.pubSubTemplate = pubSubTemplate;
    }

    // public void publishUserCreated(User)

}
