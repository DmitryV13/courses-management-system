package org.dmitry2025.userservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class AuthenticationController {
    
    @GetMapping("/token-verification")
    Boolean verifyToken(@RequestParam("token") String token){
        return true;
    }
    
    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello!");
    }
}
