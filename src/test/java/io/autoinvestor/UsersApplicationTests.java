package io.autoinvestor;

import io.autoinvestor.infrastructure.UsersEventPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
