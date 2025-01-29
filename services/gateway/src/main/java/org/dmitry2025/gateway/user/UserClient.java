package org.dmitry2025.gateway.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "user-service",
        url = "http://localhost:8222/api/v1/users"
)
public interface UserClient {
    @GetMapping("/token-verification")
    Boolean verifyToken(@RequestParam("token") String token);
}
