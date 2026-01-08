package com.supplynest.cart_service.service;

import com.supplynest.cart_service.dto.ProductPriceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8102", value = "CATALOG-SERVICE")
public interface CatalogClient {

    @GetMapping("/api/v1/catalog/products/{productId}")
    ProductPriceDto getItemUnitPrice(@PathVariable Long productId);
}
