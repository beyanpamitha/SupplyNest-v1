package com.supplynest.order_service.entity;

import com.supplynest.order_service.entity.enums.OrderStatus;
import com.supplynest.order_service.entity.enums.PaymentStatus;
import com.supplynest.order_service.entity.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long customerId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //CREATED, PAID, CANCELLED
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType; //(COD, ONLINE)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus; //(PAID, PENDING, FAILED)

    private BigDecimal totalAmount;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<OrderItems> orderItems = new ArrayList<>();

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}