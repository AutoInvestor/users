package io.autoinvestor.ui.TestEndpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ControllerTest {

    @GetMapping("/api/testUsers")
    public String handler(@RequestHeader Map<String, String> headers) {
        StringBuilder response = new StringBuilder("Received headers:\n");
        headers.forEach((key, value) -> response.append(key).append(": ").append(value).append("\n"));
        return response.toString();
    }
}
