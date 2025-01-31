package org.dmitry2025.gateway.user;

import java.util.List;

public record AuthorizationResponse (
        Boolean success,
        String login,
        List<String> authorities
) {};
