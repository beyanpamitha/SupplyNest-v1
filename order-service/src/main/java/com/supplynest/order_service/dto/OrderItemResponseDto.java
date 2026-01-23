package com.supplynest.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemResponseDto {
    private Long productId;
    private Long vendorId;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal subTotal;
}
