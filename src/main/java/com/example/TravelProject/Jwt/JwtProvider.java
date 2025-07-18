package com.example.TravelProject.Jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
 private Key key;
 private final long ACCESS_TOKEN_VALIDITY = 1000L * 60 * 60;      // 1시간
 private final long REFRESH_TOKEN_VALIDITY = 1000L * 60 * 60 * 24; // 24시간

 @PostConstruct
 public void init() {
     // 랜덤 키 생성 (실무에선 환경변수나 Vault 등에서 관리)
     key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
 }

 public String createAccessToken(String subject) {
     Date now = new Date();
     return Jwts.builder()
             .setSubject(subject)
             .setIssuedAt(now)
             .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALIDITY))
             .signWith(key)
             .compact();
 }

 public String createRefreshToken(String subject) {
     Date now = new Date();
     return Jwts.builder()
             .setSubject(subject)
             .setIssuedAt(now)
             .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALIDITY))
             .signWith(key)
             .compact();
 }

 public Jws<Claims> parseToken(String token) throws JwtException {
     return Jwts.parserBuilder()
             .setSigningKey(key)
             .build()
             .parseClaimsJws(token);
 }
}
