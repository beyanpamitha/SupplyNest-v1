package com.suuplynest.authentication_service.service;

import com.suuplynest.authentication_service.dto.LoginRequestDto;
import com.suuplynest.authentication_service.dto.LoginResponseDto;

public interface JwtService {
    String createJwtToken(String username);
}
