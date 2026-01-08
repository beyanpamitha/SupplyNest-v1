package com.supplynest.catalog_service.controller;

import com.supplynest.catalog_service.dto.ProductPriceDto;
import com.supplynest.catalog_service.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductPriceDto> getItemUnitPrice(@PathVariable Long productId){
        ProductPriceDto receivedItemPrice = catalogService.getItemUnitPrice(productId);
        return new ResponseEntity<>(receivedItemPrice, HttpStatus.OK);
    }
}
