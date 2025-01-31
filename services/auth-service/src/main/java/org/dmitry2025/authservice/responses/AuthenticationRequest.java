package org.dmitry2025.authservice.responses;

import jakarta.persistence.Column;

public class AuthenticationRequest {
    private String username;
    private String password;
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
}
