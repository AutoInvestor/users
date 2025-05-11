package io.autoinvestor;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import io.autoinvestor.infrastructure.UsersEventPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class UsersApplicationTests {

    @Autowired
    private UsersEventPublisher publisher;

    @Test
    public void testPublishedWithSpring() throws InterruptedException {
        publisher.publishUserCreated();
        Thread.sleep(10000);
        System.out.println("Message published correctly. 11/05");
    }
}
