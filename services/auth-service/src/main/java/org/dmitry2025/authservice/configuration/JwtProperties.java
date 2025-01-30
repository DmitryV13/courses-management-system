package org.dmitry2025.authservice.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
@ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = false)
public class JwtProperties {
    private String secretKey256;
    private String secretKey512;
    
    public String getSecretKey256() {
        return secretKey256;
    }
    
    public String getSecretKey512() {
        return secretKey512;
    }
    
    public void setSecretKey256(String secretKey256) {
        this.secretKey256 = secretKey256;
    }
    
    public void setSecretKey512(String secretKey512) {
        this.secretKey512 = secretKey512;
    }
    
    public void createSecretKey256(){
        SecretKey key = Jwts.SIG.HS256.key().build();
        secretKey256 = Encoders.BASE64.encode(key.getEncoded());
    }
    
    public void createSecretKey512(){
        SecretKey key = Jwts.SIG.HS512.key().build();
        secretKey512 = Encoders.BASE64.encode(key.getEncoded());
    }
}
