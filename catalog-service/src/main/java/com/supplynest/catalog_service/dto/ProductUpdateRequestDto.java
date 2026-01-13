package com.supplynest.catalog_service.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductUpdateRequestDto {
    private String category;
    private String productName;
    private String description;
    private Double unitPrice;
    private Double availableStock;
    private Integer minStockThreshold;
    private Boolean activeStatus;
}
