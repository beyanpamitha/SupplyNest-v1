package com.supplynest.catalog_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductPriceDto {
    private Long productId;
    private String productName;
    private Double unitPrice;
    private Boolean activeStatus;
}
