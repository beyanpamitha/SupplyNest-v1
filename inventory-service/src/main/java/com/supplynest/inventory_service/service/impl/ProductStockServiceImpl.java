package com.supplynest.inventory_service.service.impl;

import com.supplynest.inventory_service.dto.ReserveStockRequestDto;
import com.supplynest.inventory_service.dto.UpdateStockRequestDto;
import com.supplynest.inventory_service.entity.InventoryLogs;
import com.supplynest.inventory_service.entity.ProductStock;
import com.supplynest.inventory_service.entity.enums.ChangeType;
import com.supplynest.inventory_service.repository.InventoryLogsRepo;
import com.supplynest.inventory_service.repository.ProductStockRepo;
import com.supplynest.inventory_service.service.ProductStockService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductStockServiceImpl implements ProductStockService {

    private final ProductStockRepo productStockRepo;
    private final InventoryLogsRepo inventoryLogsRepo;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public void reserveStock(ReserveStockRequestDto reserveStockRequestDto) {

        ProductStock stock = productStockRepo.findByProductId(reserveStockRequestDto.getProductId())
                .orElseThrow(()->
                        new RuntimeException(
                                "Stock not found for product " + reserveStockRequestDto.getProductId()
                        )
                );

        //Validating the availability of stock
        if (stock.getAvailableQuantity().compareTo(reserveStockRequestDto.getQuantity()) < 0){   //Here we can't validate this like .getAvailableQuantity < .getQuantity, because it does not support with BigDecimal.
            throw new RuntimeException(
                    "Insufficient stock for product " + reserveStockRequestDto.getProductId()
            );
        }

        //Reserving the stock
        stock.setAvailableQuantity(
                stock.getAvailableQuantity().subtract(reserveStockRequestDto.getQuantity())  //Can't subtract like usual because of BigDecimal
        );

        stock.setReservedQuantity(
                stock.getReservedQuantity().add(reserveStockRequestDto.getQuantity())
        );

        //Saving
        productStockRepo.save(stock);

        //Audit logs
        InventoryLogs log = new InventoryLogs();

        log.setProductId(reserveStockRequestDto.getProductId());
        log.setChangeType(ChangeType.RESERVE);
        log.setQuantity(reserveStockRequestDto.getQuantity());
        log.setOrderId(reserveStockRequestDto.getOrderId());

        inventoryLogsRepo.save(log);
    }

    @Override
    @Transactional
    public void releaseStock(ReserveStockRequestDto reserveStockRequestDto) {
        ProductStock stock = productStockRepo.findByProductId(reserveStockRequestDto.getProductId())
                .orElseThrow(()->
                        new RuntimeException(
                                "Stock not found for product " + reserveStockRequestDto.getProductId()
                        )
                );

        //Checking reserved quantity
        if (stock.getReservedQuantity().compareTo(reserveStockRequestDto.getQuantity()) < 0){
            throw new RuntimeException("Cannot release more than reserved stock for product");
        }

        //Decreasing reserved quantity
        stock.setReservedQuantity(
                stock.getReservedQuantity().subtract(reserveStockRequestDto.getQuantity())
        );

        //Increasing available quantity
        stock.setAvailableQuantity(
                stock.getAvailableQuantity().add(reserveStockRequestDto.getQuantity())
        );

        productStockRepo.save(stock);

        //Audit logs
        InventoryLogs log = new InventoryLogs();

        log.setProductId(reserveStockRequestDto.getProductId());
        log.setChangeType(ChangeType.RELEASE);
        log.setQuantity(reserveStockRequestDto.getQuantity());
        log.setOrderId(reserveStockRequestDto.getOrderId());

        inventoryLogsRepo.save(log);
    }

    @Override
    @Transactional
    public void confirmStock(ReserveStockRequestDto reserveStockRequestDto) {

        ProductStock stock = productStockRepo.findByProductId(reserveStockRequestDto.getProductId())
                .orElseThrow(()->
                        new RuntimeException(
                                "Stock not found for product " + reserveStockRequestDto.getProductId()
                        )
                );

        //Validating reserved quantity
        if (stock.getReservedQuantity().compareTo(reserveStockRequestDto.getQuantity())<0){
            throw new RuntimeException(
                    "Insufficient stock"
            );
        }

        //Permanently decreasing from reserved quantity
        stock.setReservedQuantity(
                stock.getReservedQuantity().subtract(reserveStockRequestDto.getQuantity())
        );

        productStockRepo.save(stock);

        //Audit logs
        InventoryLogs log = new InventoryLogs();
        log.setProductId(reserveStockRequestDto.getProductId());
        log.setChangeType(ChangeType.DECREASE);
        log.setQuantity(reserveStockRequestDto.getQuantity());
        log.setOrderId(reserveStockRequestDto.getOrderId());

        inventoryLogsRepo.save(log);

    }

    @Override
    @Transactional
    public UpdateStockRequestDto updateStock(UpdateStockRequestDto updateStockRequestDto) {

        if (updateStockRequestDto.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Stock quantity must be greater than zero");
        }

        ProductStock stock = productStockRepo.findByProductId(updateStockRequestDto.getProductId())
                .orElse(null); //No need to throw exceptions because if a product was not found under relevant id, we create new product details in the entity.
        
        if (stock == null){
            //Create new stock record
            stock = new ProductStock();
            stock.setProductId(updateStockRequestDto.getProductId());
            stock.setVendorId(updateStockRequestDto.getVendorId());
            stock.setAvailableQuantity(updateStockRequestDto.getQuantity());
            stock.setReservedQuantity(BigDecimal.ZERO);
        }else {
            //Increase quantity
            stock.setAvailableQuantity(
                    stock.getAvailableQuantity().add(updateStockRequestDto.getQuantity())
            );
        }

        ProductStock updatedProduct = productStockRepo.save(stock);

        //Audit logs
        InventoryLogs log = new InventoryLogs();
        log.setProductId(updateStockRequestDto.getProductId());
        log.setChangeType(ChangeType.INCREASE);
        log.setQuantity(updateStockRequestDto.getQuantity());
        log.setOrderId(null);

        inventoryLogsRepo.save(log);

        return modelMapper.map(updatedProduct, UpdateStockRequestDto.class);
    }
}
