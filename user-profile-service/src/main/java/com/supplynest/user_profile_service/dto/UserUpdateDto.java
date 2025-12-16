package com.supplynest.user_profile_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateDto {

    private String username;
    private String email;
    private String role;
    private String status;
}
