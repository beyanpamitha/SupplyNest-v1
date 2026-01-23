package com.supplynest.order_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    private Long productId;
    private Long vendorId;
    private BigDecimal quantity;
    @Column(precision = 19, scale = 4)
    private BigDecimal unitPrice;
    private BigDecimal subTotal;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}