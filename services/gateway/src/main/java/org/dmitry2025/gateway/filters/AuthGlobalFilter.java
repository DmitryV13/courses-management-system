package org.dmitry2025.gateway.filters;

import org.dmitry2025.gateway.user.AuthClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    
    private final AuthClient authClient;
    
    public AuthGlobalFilter(@Lazy AuthClient authClient) {
        this.authClient = authClient;
    }
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        
        if(
                !path.startsWith("/api/v1/auth/register") &&
                !path.startsWith("/api/v1/auth/authenticate")
        ) {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                var authToken = authHeader.substring(7);
                var response = authClient.verifyToken(authToken)
                        .orElseThrow(()-> new RuntimeException("Invalid response"));
                if (response) {
                    return chain.filter(exchange);
                } else {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            } else {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return -1; // priority of this filter is set to the highest
    }
}

