package org.dmitry2025.gateway.configurations;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public HttpMessageConverters customConverters() {
        return new HttpMessageConverters();
    }
}
