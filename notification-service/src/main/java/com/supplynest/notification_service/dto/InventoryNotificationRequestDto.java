package com.supplynest.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryNotificationRequestDto {
    private Long productId;
    private Long vendorId;
    private BigDecimal availableStock;
}
