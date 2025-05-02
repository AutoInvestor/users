package io.autoinvestor.ui.TestEndpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @GetMapping("/api/testUsers")
    public String handler() {
        return "Congrats. You reached the users service.";
    }
}
