package com.example.sinta.util;

import com.example.sinta.domain.Extractable;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public final class JwtUtil {

    /*
    This is a random key that always generated every time start our application
     */
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static final JwtUtil INSTANCE = new JwtUtil();

    private JwtUtil(){}

    public String generateJwtToken(Extractable extractable){
        return Jwts.builder()
                .signWith(key)
                .claim("id", extractable.getId())
                .claim("nama", extractable.getNama())
                .claim("createdAt", extractable.getCreatedAt())
                .claim("role", extractable.getRole())
                .compact();
    }

    public Key getKey() {
        return key;
    }
}
