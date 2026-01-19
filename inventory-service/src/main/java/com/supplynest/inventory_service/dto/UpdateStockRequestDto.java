package com.supplynest.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateStockRequestDto {
    private Long productId;
    private Long vendorId;
    private BigDecimal quantity;
}
