package com.suuplynest.authentication_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
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
