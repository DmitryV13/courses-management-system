package org.dmitry2025.gateway.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "auth-service",
        url = "http://localhost:8000/api/v1/auth"
)
public interface AuthClient {
    @GetMapping("/token-verification")
    Boolean verifyToken(@RequestParam("token") String token);
}
