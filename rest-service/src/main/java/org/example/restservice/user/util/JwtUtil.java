package org.example.restservice.user.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.example.restservice.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret.access}")
    private String jwtSecretAccess;

    @Value("${jwt.secret.refresh}")
    private String jwtSecretRefresh;

    public JwtUtil(){
        this.jwtSecretAccess = Encoders.BASE64.encode(jwtSecretAccess.getBytes());
        this.jwtSecretRefresh = Encoders.BASE64.encode(jwtSecretRefresh.getBytes());
    }

    public String generateAccessToken(@NonNull UserDTO user){
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts.builder()
                .subject(user.getEmail())
                .expiration(accessExpiration)
                .signWith(Keys.hmacShaKeyFor(jwtSecretAccess.getBytes()))
                .claim("roles", "USER")
                .compact();
    }


    public String generateRefreshToken(@NonNull UserDTO user){
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);
        return Jwts.builder()
                .subject(user.getEmail())
                .expiration(refreshExpiration)
                .signWith(Keys.hmacShaKeyFor(jwtSecretRefresh.getBytes()))
                .compact();
    }

    public boolean validateAccessToken(@NonNull String accessToken){
        return validateToken(accessToken, jwtSecretAccess);
    }

    public boolean validateRefreshToken(@NonNull String refreshToken){
        return validateToken(refreshToken, jwtSecretRefresh);
    }

    private boolean validateToken(@NonNull String token, @NonNull String secret){
        try{
            Jwts.parser()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (RuntimeException ex) {
            return false;
        }
    }


    public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, jwtSecretAccess);
    }

    public Claims getRefreshClaims(@NonNull String token) {
        return getClaims(token, jwtSecretRefresh);
    }

    private Claims getClaims(@NonNull String token, @NonNull String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
