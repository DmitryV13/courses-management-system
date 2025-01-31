package org.dmitry2025.authservice.services;

import org.dmitry2025.authservice.configuration.JwtUtils;
import org.dmitry2025.authservice.configuration.SecureUserDetails;
import org.dmitry2025.authservice.entities.Authority;
import org.dmitry2025.authservice.entities.User;
import org.dmitry2025.authservice.repositories.UserRepository;
import org.dmitry2025.authservice.responses.AuthenticationRequest;
import org.dmitry2025.authservice.responses.AuthenticationResponse;
import org.dmitry2025.authservice.responses.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    
    public AuthService(
            JwtUtils jwtUtils,
            UserDetailsService userDetailsService,
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Boolean verifyToken(String token) {
        var login = jwtUtils.extractLogin(token);
        if (login != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(login);
            var v = jwtUtils.isTokenValid(token, userDetails);
            return jwtUtils.isTokenValid(token, userDetails);
        }
        return false;
    }
    
    public String register(RegisterRequest request) {
        var userAuthority = new Authority(request.getRole());
        var user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );
        user.getAuthorities().add(userAuthority);
        
        userRepository.save(user);
        return "REGISTRATION_SUCCESSFULLY";
    }
    
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        
        var secureUserDetails = new SecureUserDetails(user);
        var jwtToken = jwtUtils.generateToken(secureUserDetails);
        return new AuthenticationResponse(jwtToken);
    }
}
