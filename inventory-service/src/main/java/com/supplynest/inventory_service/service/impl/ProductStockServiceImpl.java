package com.supplynest.inventory_service.service.impl;

import com.supplynest.inventory_service.dto.ReserveStockRequestDto;
import com.supplynest.inventory_service.repository.InventoryLogsRepo;
import com.supplynest.inventory_service.repository.ProductStockRepo;
import com.supplynest.inventory_service.service.ProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductStockServiceImpl implements ProductStockService {

    private final ProductStockRepo productStockRepo;
    private final InventoryLogsRepo inventoryLogsRepo;
    
    @Override
    public void reserveStock(ReserveStockRequestDto reserveStockRequestDto) {

    }
}
