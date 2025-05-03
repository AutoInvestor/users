package io.autoinvestor.ui.RequestId;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userId")
public class ControllerRequestId {

    @PostMapping
    public String getUserId(@RequestBody EmailRequest email) {
        if (email.getEmail().equals("mate.alfredo98@gmail.com")) {
            return "12345";
        } else {
            return "67890";
        }
    }
}
