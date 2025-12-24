package com.suuplynest.authentication_service.controller;

import com.suuplynest.authentication_service.dto.LoginRequestDto;
import com.suuplynest.authentication_service.dto.LoginResponseDto;
import com.suuplynest.authentication_service.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public LoginResponseDto createJwtTokenAndLogin(@RequestBody LoginRequestDto loginRequestDto) throws Exception{
return jwtService.cwcs
    }
}
