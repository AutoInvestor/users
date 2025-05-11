package io.autoinvestor;

import static javax.management.Query.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import io.autoinvestor.infrastructure.UserCreatedMessage;
import io.autoinvestor.infrastructure.UserCreatedMessagePayload;
import io.autoinvestor.infrastructure.UsersEventPublisher;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
class UsersApplicationTests {



    @Test
    void shouldPublishSerializedUserCreatedMessage() throws Exception {
        // Given
        PubSubTemplate pubSubTemplate = mock(PubSubTemplate.class);
        UsersEventPublisher publisher = new UsersEventPublisher(pubSubTemplate);

        UserCreatedMessagePayload payload = new UserCreatedMessagePayload("John", "Dow", "john.doe@example.com", 3);

        UserCreatedMessage message = new UserCreatedMessage("event-123", new Date(), "agg-456", 1, "USER_CREATED",
                payload);

        // Mock the publish method to return a completed future
        when(pubSubTemplate.publish(eq("users"), anyString(), anyMap()))
                .thenReturn(CompletableFuture.completedFuture("message-id-123"));

        // When
        publisher.publishUserCreated(List.of(message));

        // Then
        ArgumentCaptor<String> payloadCaptor = ArgumentCaptor.forClass(String.class);

        verify(pubSubTemplate).publish(eq("users"), payloadCaptor.capture(), anyMap());

        String publishedJson = payloadCaptor.getValue();

        // Validate JSON contents
        assertTrue(publishedJson.contains("\"firstName\":\"John\""));
        assertTrue(publishedJson.contains("\"email\":\"john.doe@example.com\""));
    }
}
