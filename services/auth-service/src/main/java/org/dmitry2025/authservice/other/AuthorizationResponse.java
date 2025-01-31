package org.dmitry2025.authservice.other;

import java.util.List;

public record AuthorizationResponse (
        Boolean success,
        String login,
        List<String> authorities
) {};

