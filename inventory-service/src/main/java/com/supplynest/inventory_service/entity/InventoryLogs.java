package com.supplynest.inventory_service.entity;

import com.supplynest.inventory_service.entity.enums.ChangeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_logs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    @Enumerated(EnumType.STRING)
    private ChangeType changeType;   //used an enum for types

    private BigDecimal quantity;
    private Long orderId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}