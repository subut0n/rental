package com.example.chatoprentalbackend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private static final String SECRET_KEY = "4DA0438ED22A5260827B71AE232BBC694EF1DD185275C14A8AA6AD10608AEDC8";

    /**
     * Extracts the username from the JWT.
     *
     * @param token The JWT from which the username is to be extracted.
     * @return The username (subject) from the JWT.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generic method to extract any claim from the JWT.
     *
     * @param token The JWT from which the claim is to be extracted.
     * @param claimsResolver A function defining the claim to extract.
     * @return The requested claim from the JWT.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a JWT for the given UserDetails.
     *
     * @param userDetails The user details for which the token is to be generated.
     * @return A JWT as a String.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT with additional claims.
     *
     * @param extraClaims Additional claims to be included in the JWT.
     * @param userDetails The user details for which the token is to be generated.
     * @return A JWT as a String.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims) // Includes custom claims provided by the caller.
                .setSubject(userDetails.getUsername()) // Sets the subject as the user's username.
                .setIssuedAt(new Date(System.currentTimeMillis())) // Marks the current time as the issue time.
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // Sets the token's expiration time to 24 hours.
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Signs the JWT with the specified key and algorithm.
                .compact(); // Compacts the JWT into a URL-safe string.
    }
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        // Initiates the JWT parsing process to extract all claims (payload) from the given token.
        return Jwts
                // Begin building a JWT parser to validate and parse the token.
                .parserBuilder()
                // Set the signing key used to validate the token's signature. This ensures the token was signed by a trusted source.
                // The getSignInKey() method should return the key used to sign the JWT. This key must match the one used to sign the token.
                .setSigningKey(getSignInKey())
                // Finalize the parser configuration and create a parser instance.
                .build()
                // Parse the provided JWT string (token), validate its signature with the set key, and if valid, extract the claims.
                .parseClaimsJws(token)
                // Retrieve the claims (payload) part of the JWT. This contains all the information (claims) stored in the token.
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
