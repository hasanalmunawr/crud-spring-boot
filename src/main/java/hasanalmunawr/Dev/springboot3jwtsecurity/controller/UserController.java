package hasanalmunawr.Dev.springboot3jwtsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @PostMapping
    public String post() {
        return "POST:: USER controller";
    }

    @GetMapping
    public String get() {
        return "GET:: USER controller";
    }

    @PutMapping
    public String put() {
        return "PUT:: USER controller";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE:: USER controller";
    }
}
