package com.supplynest.catalog_service.service;

import com.supplynest.catalog_service.dto.ProductPriceDto;
import com.supplynest.catalog_service.dto.ProductRequestDto;
import com.supplynest.catalog_service.dto.ProductsDto;

public interface CatalogService {
    ProductPriceDto getItemUnitPrice(Long productId);

    ProductsDto addProduct(ProductRequestDto productRequestDto, Long vendorId);

    ProductsDto removeProduct(Long productId);

    ProductsDto updateProduct(Long productId, ProductRequestDto productRequestDto);

    ProductsDto updateUnitPrice(Long productId, Double unitPrice);

    ProductsDto getProductById(Long productId);
}
