package com.supplynest.order_service.dto;

import com.supplynest.order_service.entity.enums.OrderStatus;
import com.supplynest.order_service.entity.enums.PaymentStatus;
import com.supplynest.order_service.entity.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponseDto {
    private Long orderId;
    private Long customerId;
    private OrderStatus status;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    private List<OrderItemResponseDto> items;
}
