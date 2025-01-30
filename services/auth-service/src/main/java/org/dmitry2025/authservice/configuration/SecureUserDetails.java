package org.dmitry2025.authservice.configuration;

import org.dmitry2025.authservice.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecureUserDetails implements UserDetails {
    private String username;
    
    public SecureUserDetails(User user) {
        this.username = user.getUsername();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    
    @Override
    public String getPassword() {
        return "";
    }
    
    @Override
    public String getUsername() {
        return username;
    }
}
