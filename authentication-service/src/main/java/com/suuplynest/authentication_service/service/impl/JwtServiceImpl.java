package com.suuplynest.authentication_service.service.impl;

import com.suuplynest.authentication_service.dto.LoginRequestDto;
import com.suuplynest.authentication_service.dto.LoginResponseDto;
import com.suuplynest.authentication_service.entity.Users;
import com.suuplynest.authentication_service.repository.UserRepo;
import com.suuplynest.authentication_service.service.JwtService;
import com.suuplynest.authentication_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService, UserDetailsService {

    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with username: " + username
                        )
                );
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole()) // "ROLE_ADMIN", "ROLE_USER"
                .build();
    }

    @Override
    public LoginResponseDto createJwtToken(LoginRequestDto loginRequestDto) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();
        authenticate(username,password);

        UserDetails userDetails = loadUserByUsername(username);
        String generatedToken = jwtUtil.generateNewToken(userDetails);
        Users user = userRepo.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with username: " + username
                        )
                );;
        return new LoginResponseDto(
                user,
                generatedToken
        );
    }

    public void authenticate(String username, String password) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

    }
}
