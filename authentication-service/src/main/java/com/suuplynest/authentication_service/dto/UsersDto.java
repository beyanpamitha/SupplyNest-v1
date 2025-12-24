package com.suuplynest.authentication_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String status;
    private LocalDateTime createdAt;
}
