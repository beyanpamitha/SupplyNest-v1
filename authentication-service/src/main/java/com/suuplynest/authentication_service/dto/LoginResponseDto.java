package com.suuplynest.authentication_service.dto;

import com.suuplynest.authentication_service.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponseDto {
    private Users user;
    private String jwtToken;
}
