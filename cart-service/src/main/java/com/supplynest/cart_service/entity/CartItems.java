package com.supplynest.cart_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cartId;
    private Long productId;
    private Double quantity;
    private Double unitPrice;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime updatedAt;
}
