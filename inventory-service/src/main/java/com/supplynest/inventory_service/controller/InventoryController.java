package com.supplynest.inventory_service.controller;

import com.supplynest.inventory_service.dto.ReserveStockRequestDto;
import com.supplynest.inventory_service.dto.UpdateStockRequestDto;
import com.supplynest.inventory_service.service.ProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final ProductStockService productStockService;

    @PostMapping("/reserve-stock")
    public ResponseEntity<Void> reserveStock(@RequestBody ReserveStockRequestDto reserveStockRequestDto){
        productStockService.reserveStock(reserveStockRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/release-stock")
    public ResponseEntity<Void> releaseStock(@RequestBody ReserveStockRequestDto reserveStockRequestDto){
        productStockService.releaseStock(reserveStockRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/confirm-stock")
    public ResponseEntity<Void> confirmStockSale(@RequestBody ReserveStockRequestDto reserveStockRequestDto){
        productStockService.confirmStock(reserveStockRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update-stock")
    public ResponseEntity<UpdateStockRequestDto> updateStock(@RequestBody UpdateStockRequestDto updateStockRequestDto){
        UpdateStockRequestDto updatedStock = productStockService.updateStock(updateStockRequestDto);
        return new ResponseEntity<>(updatedStock, HttpStatus.OK);
    }
}
