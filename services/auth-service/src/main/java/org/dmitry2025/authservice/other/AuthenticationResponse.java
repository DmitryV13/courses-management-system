package org.dmitry2025.authservice.other;

public class AuthenticationResponse {
    private String token;
    public AuthenticationResponse(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}
