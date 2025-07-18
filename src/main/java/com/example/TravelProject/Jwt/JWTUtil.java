package com.example.TravelProject.Jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .build()
                .parseClaimsJws(token)      // parseClaimsJwt → parseClaimsJws 변경
                .getBody()
                .get("username", String.class);
    }

    public String getRole(String token) {
        return Jwts.parserBuilder()
                .build()
                .parseClaimsJws(token)    // 변경
                .getBody()
                .get("role", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parserBuilder()
                .build()
                .parseClaimsJws(token)    // 변경
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public String createJwt(String username, String role, Long expiredMs) {
        Claims claims = Jwts.claims();
        claims.put("username", username);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256)) // signWith 필요
                .compact();
    }

}
