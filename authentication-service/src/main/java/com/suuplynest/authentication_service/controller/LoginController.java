package com.suuplynest.authentication_service.controller;

import com.suuplynest.authentication_service.dto.LoginRequestDto;
import com.suuplynest.authentication_service.dto.LoginResponseDto;
import com.suuplynest.authentication_service.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class LoginController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public LoginResponseDto createJwtTokenAndLogin(@RequestBody LoginRequestDto loginRequestDto) {

        //Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );

        String token = jwtService.createJwtToken(authentication.getName());

        return new LoginResponseDto(token);
    }
}
