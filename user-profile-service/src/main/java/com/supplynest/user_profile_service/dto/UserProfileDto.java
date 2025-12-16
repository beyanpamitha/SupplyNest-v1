package com.supplynest.user_profile_service.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String status;
    private LocalDateTime createdAt;
}
