package com.supplynest.api_gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;

@Component
public class JwtUtil {

    private static final String SECRET =
            "u8Zy4R1L3eJ9r6Vx2hQm5TzW8dP7aS3bF0vY6nC1kL9qH4jR2sM7wX8pQ0tZ3yVx";

    private final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

}
