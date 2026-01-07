package com.supplynest.api_gateway.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {

        String path = request.getRequestURI();

        //Allow auth endpoints without JWT
        if (path.startsWith("/api/v1/auth")) {
            return true;
        }

        //Read Authorization header
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        try {
            String token = authHeader.substring(7);


            //Extract userId from JWT
            Long userId = jwtUtil.extractUserId(token);

            //Forward identity to downstream services
            request.setAttribute(
                    "org.springframework.cloud.gateway.server.mvc.common.MVC_REQUEST_HEADERS.X-USER-ID",
                    userId.toString()
            );

            return true;

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
