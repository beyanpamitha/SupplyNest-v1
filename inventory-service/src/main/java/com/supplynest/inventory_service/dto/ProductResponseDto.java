package com.supplynest.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponseDto {
    private Long productId;
    private BigDecimal availableQuantity;
    private BigDecimal reservedQuantity;
    private LocalDateTime updatedAt;
}
