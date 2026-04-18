package com.omkar.porfolio_backend.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static  final String SECRET="This the best key i have ever seen in my i love you shivani";
    private static  final long EXPIRATION_TIME=60*60*1000;
    public static Key getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
    public static String generateToken(String username){
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME)).signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();

    }
    public static  String extractUsername(String token){
        Claims claims=Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    public static boolean isTokenValid(String token) {
        try {
            extractUsername(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
