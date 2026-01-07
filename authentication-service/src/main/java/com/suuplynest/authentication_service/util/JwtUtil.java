package com.suuplynest.authentication_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final String SECRET =
            "u8Zy4R1L3eJ9r6Vx2hQm5TzW8dP7aS3bF0vY6nC1kL9qH4jR2sM7wX8pQ0tZ3yVx"; //Should use at least 512bit key with HS512
    private static final Key KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    private static final long TOKEN_VALIDITY = 1000 * 60 * 60 * 5; // 5 hours

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Validating the token by username matching and token expiration
    public boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);

        return (username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token)); //Here, I have simplified the if statement by single line.
    }

    //Checking the expiration of token
    public boolean isTokenExpired(String token){
        final Date expiration = getClaimFromToken(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    //Generate new token
    public String generateNewToken(UserDetails userDetails, Long userId){

        //Creating a key-value pair
        Map<String, Object> claims = new HashMap<>();

        //Add role to claims
        claims.put("userId", userId);
        claims.put("role", userDetails.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .toList()
        );

        //Embed the authenticated user’s ID as a custom JWT claim so downstream microservices can securely identify the user without trusting client input.

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) //Getting username from frontend(user details)
                .setIssuedAt(new Date(System.currentTimeMillis())) //Setting the issued time to current system time
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY)) //Setting the expiration time to token validity time * 1000 seconds.
                .signWith(KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.get("userId", Long.class));
    }
}
