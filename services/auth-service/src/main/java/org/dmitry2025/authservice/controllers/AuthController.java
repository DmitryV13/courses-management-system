package org.dmitry2025.authservice.controllers;

import org.dmitry2025.authservice.responses.AuthenticationRequest;
import org.dmitry2025.authservice.responses.AuthenticationResponse;
import org.dmitry2025.authservice.responses.RegisterRequest;
import org.dmitry2025.authservice.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @GetMapping("/token-verification")
    Optional<Boolean> verifyToken(@RequestParam("token") String token){
        return Optional.of(authService.verifyToken(token));
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
