package com.jowynd.ecommerce.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jowynd.ecommerce.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //defined in application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("security-ecommerce-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(getExpirationTime())
                    .sign(algorithm);

            return token;

        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error while generating token!", e);
        }
    }

    public String validateToken(String token) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return token = JWT.require(algorithm)
                    .withIssuer("security-ecommerce-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant getExpirationTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
