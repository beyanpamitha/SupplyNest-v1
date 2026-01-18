package com.supplynest.inventory_service.dto;

import java.math.BigDecimal;

public class ReserveStockRequestDto {
    private Long productId;
    private BigDecimal quantity;
    private Long orderId;
}
