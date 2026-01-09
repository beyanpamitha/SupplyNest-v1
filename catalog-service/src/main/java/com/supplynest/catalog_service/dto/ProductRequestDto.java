package com.supplynest.catalog_service.dto;

import com.supplynest.catalog_service.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDto {
    private Long vendorId;
    private String categoryName;
    private String productName;
    private String description;
    private Double unitPrice;
    private Double availableStock;
    private Integer minStockThreshold;
    private Boolean activeStatus;
}
