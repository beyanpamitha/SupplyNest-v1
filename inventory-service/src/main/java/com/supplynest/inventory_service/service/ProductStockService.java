package com.supplynest.inventory_service.service;

import com.supplynest.inventory_service.dto.ReserveStockRequestDto;

public interface ProductStockService {
    void reserveStock(ReserveStockRequestDto reserveStockRequestDto);
}
