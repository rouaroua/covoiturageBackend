package com.covoiturage.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    private SecretKey secretKey;

    public JwtUtil() {
        // Generate a secure key for HS256
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateToken(String email) {
        // Set the token expiration (e.g., 1 hour)
        Date expirationDate = new Date(System.currentTimeMillis() + 3600000); // 1 hour in milliseconds

        // Generate the token using the secretKey
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate) // Set expiration
                .signWith(secretKey)
                .compact();
    }

    // Validate the token
    public Boolean validateToken(String token, UserDetails userDetails) {
       
            final String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
       
    }

    // Extract username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract claims
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Check if the token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract expiration date
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract all claims
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey) // Use secretKey here
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException | ExpiredJwtException | UnsupportedJwtException e) {
            // Handle exception (log it or rethrow)
            return null; // Or throw a custom exception if desired
        }
    }
}
