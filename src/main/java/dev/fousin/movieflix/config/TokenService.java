package dev.fousin.movieflix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.fousin.movieflix.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {
    @Value("${movieflix.security.jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withClaim("name", user.getName())
                .withExpiresAt(Instant.now().plusSeconds(86400)) // 24 hours
                .withIssuedAt(Instant.now())
                .withIssuer("Api Movieflix")
                .sign(algorithm);
    }

    public Optional<JWTUserData> verifyToken(String token) {
        try{

        Algorithm algorithm = Algorithm.HMAC256(secret);
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token);

        return Optional.of(JWTUserData.builder()
                .id(jwt.getClaim("userId").asLong())
                .name(jwt.getClaim("name").asString())
                .email(jwt.getSubject())
                .build()
        );
        }catch (JWTVerificationException e){
            return Optional.empty();
        }


    }
}
