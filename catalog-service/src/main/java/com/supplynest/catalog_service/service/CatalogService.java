package com.supplynest.catalog_service.service;

import com.supplynest.catalog_service.dto.ProductPriceDto;

public interface CatalogService {
    ProductPriceDto getItemUnitPrice(Long productId);
}
