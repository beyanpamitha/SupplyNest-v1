package com.supplynest.inventory_service.service;

import com.supplynest.inventory_service.dto.ReserveStockRequestDto;
import com.supplynest.inventory_service.dto.UpdateStockRequestDto;

public interface ProductStockService {
    void reserveStock(ReserveStockRequestDto reserveStockRequestDto);

    void releaseStock(ReserveStockRequestDto reserveStockRequestDto);

    void confirmStock(ReserveStockRequestDto reserveStockRequestDto);

    UpdateStockRequestDto updateStock(UpdateStockRequestDto updateStockRequestDto);
}
