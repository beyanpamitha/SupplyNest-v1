package com.supplynest.cart_service.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Carts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private long customerId;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime updatedAt;
}
