package com.supplynest.user_profile_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_profile")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Column(nullable = false)
    private String email;
    private String role;
    private String status;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp   //Auto-populates on insert
    private LocalDateTime createdAt;
}
