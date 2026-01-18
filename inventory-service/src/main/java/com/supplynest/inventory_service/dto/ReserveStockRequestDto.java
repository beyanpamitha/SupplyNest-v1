package com.supplynest.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReserveStockRequestDto {
    private Long productId;
    private BigDecimal quantity;
    private Long orderId;
}
