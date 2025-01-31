package org.dmitry2025.authservice.configuration;

import org.dmitry2025.authservice.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecureUserDetails implements UserDetails {
    private String username;
    private String password;
    
    public SecureUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public String getUsername() {
        return username;
    }
}
