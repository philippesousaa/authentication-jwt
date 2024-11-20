package com.authentication.authentication.service.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.authentication.authentication.domain.users.Users;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    public String generateToken(Users users) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(users.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return  JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao validar token", e);
        }
    }

    private Instant genExpirationDate() {
        return Instant.now().plusSeconds(1000);
    }

}
