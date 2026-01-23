package com.supplynest.order_service.dto;

import com.supplynest.order_service.entity.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrderRequestDto {
    private Long customerId;
    private PaymentType paymentType;
    List<OrderItemRequestDto> items;
}
