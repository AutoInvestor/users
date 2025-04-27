package io.autoinvestor.ui.TestEndpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @GetMapping("/testUser")
    public String handler() {
        return "Congrats. You reached the users service.";
    }
}
