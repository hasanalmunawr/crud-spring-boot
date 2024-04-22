package hasanalmunawr.Dev.springboot3jwtsecurity.controller;

import hasanalmunawr.Dev.springboot3jwtsecurity.dto.AuthRequest;
import hasanalmunawr.Dev.springboot3jwtsecurity.dto.RegisterRequest;
import hasanalmunawr.Dev.springboot3jwtsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.accepted().body(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }


}
