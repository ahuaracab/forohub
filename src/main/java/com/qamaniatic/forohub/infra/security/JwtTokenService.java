package com.qamaniatic.forohub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qamaniatic.forohub.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generateToken(User user) {
        System.out.println("este es mi usuario: " + user);
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            Instant expirationDate = generateExpirationDate();
            System.out.println("Fecha de expiración: " + expirationDate);
            return JWT.create()
                    .withIssuer("forohub")
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId())
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            System.out.println("Se ejecuta la excepcion???");
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }

        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // validando firma
            verifier = JWT.require(algorithm)
                    .withIssuer("forohub")
                    .build()
                    .verify(token);
            verifier.getSubject();

        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }

        if (verifier.getSubject() == null) {
            throw new RuntimeException("Invalid Verifier");
        }
        return verifier.getSubject();
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}