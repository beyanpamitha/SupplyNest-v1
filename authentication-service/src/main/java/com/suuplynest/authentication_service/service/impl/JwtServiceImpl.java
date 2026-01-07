package com.suuplynest.authentication_service.service.impl;

import com.suuplynest.authentication_service.entity.Users;
import com.suuplynest.authentication_service.repository.UserRepo;
import com.suuplynest.authentication_service.service.JwtService;
import com.suuplynest.authentication_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;

    @Override
    public String createJwtToken(String username) {

        Users user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserDetails userDetails = User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
        return jwtUtil.generateNewToken(userDetails,user.getUserId());
    }
}
