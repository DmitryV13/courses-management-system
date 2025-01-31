package org.dmitry2025.authservice.configuration;

import org.dmitry2025.authservice.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SecureUserDetails implements UserDetails {
    private String username;
    private String password;
    private List<SimpleGrantedAuthority> authorities;
    
    public SecureUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        authorities = user.getAuthorities().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getName()))
                .toList();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
