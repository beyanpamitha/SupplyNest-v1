package com.supplynest.inventory_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_stock")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Long vendorId;

    @Column(nullable = false)
    private BigDecimal availableQuantity;
    private BigDecimal reservedQuantity;

    @Version
    private Long version;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}