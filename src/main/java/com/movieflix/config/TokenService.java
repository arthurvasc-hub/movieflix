package com.movieflix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.movieflix.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class TokenService {

    @Value("${movieflix.security.secret}")
    private String secret;

    public String generateToken(User user){


        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withClaim("name", user.getName())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuer("API Movieflix")
                .sign(Algorithm.HMAC256(secret));
    }

    public Optional<JWTUserData> verifyToken(String token){
        try {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        DecodedJWT jwt = JWT.require(algorithm)
                .build()
                .verify(token);

        return Optional.of(JWTUserData
                .builder()
                .id(jwt.getClaim("userId").asLong())
                .name(jwt.getClaim("name").asString())
                .email(jwt.getSubject())
                .build());

        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}
