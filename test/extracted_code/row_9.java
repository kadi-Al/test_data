package com.example.demo.model;

public class Payload {
    private String name;

    // Getters and setters (or use Lombok to avoid boilerplate code)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
package com.example.demo.controller;

import com.example.demo.model.Payload;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping(value = "/payload", consumes = "application/json")
    public String handlePayload(@RequestBody Payload payload) {
        return payload.getName(); // Return the name from the deserialized object
    }
}
bash
curl -X POST http://localhost:8080/api/payload -H "Content-Type: application/json" -d '{"name":"John Doe"}'