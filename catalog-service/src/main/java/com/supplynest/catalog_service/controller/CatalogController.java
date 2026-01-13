package com.supplynest.catalog_service.controller;

import com.supplynest.catalog_service.dto.ProductPriceDto;
import com.supplynest.catalog_service.dto.ProductRequestDto;
import com.supplynest.catalog_service.dto.ProductsDto;
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

    @PostMapping("/products/add-item")
    public ResponseEntity<ProductsDto> addProducts(@RequestBody ProductRequestDto productRequestDto){
        ProductsDto product = catalogService.addProduct(productRequestDto, productRequestDto.getVendorId());
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<ProductsDto> removeProducts(@PathVariable Long productId){
        ProductsDto product = catalogService.removeProduct(productId);
        return  new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductsDto> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductRequestDto productRequestDto
    ){
        ProductsDto product = catalogService.updateProduct(productId, productRequestDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/products/unit-price/{productId}")
    public ResponseEntity<ProductsDto> updateUnitPrice(
            @PathVariable Long productId,
            @RequestParam Double unitPrice
    ){
        ProductsDto product = catalogService.updateUnitPrice(productId,unitPrice);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
