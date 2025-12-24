package com.suuplynest.authentication_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    public static final String SECRET_KEY = "123456789";
    public static final int TOKEN_VALIDITY = 3600 * 5;

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
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
    public String generateNewToken(UserDetails userDetails){

        //Creating a key-value pair
        Map<String, Object> claims = new HashMap<>();

        //Add role to claims
        claims.put("role", userDetails.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .toList()
        );

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) //Getting username from frontend(user details)
                .setIssuedAt(new Date(System.currentTimeMillis())) //Setting the issued time to current system time
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000)) //Setting the expiration time to token validity time * 1000 seconds.
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();}
}
