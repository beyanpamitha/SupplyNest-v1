package com.supplynest.order_service.controller;

import com.supplynest.order_service.dto.CreateOrderRequestDto;
import com.supplynest.order_service.dto.OrderResponseDto;
import com.supplynest.order_service.dto.PaymentStatusUpdateDto;
import com.supplynest.order_service.entity.enums.PaymentStatus;
import com.supplynest.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("create-order")
    public ResponseEntity<OrderResponseDto> createNewOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto){
        OrderResponseDto order = orderService.createNewOrder(createOrderRequestDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("{/orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long orderId){
        OrderResponseDto order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @GetMapping("users/{customerId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByCustomer(@PathVariable Long customerId){
        List<OrderResponseDto> orders = orderService.getOrdersByCustomer(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("{/orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId){
        Void order = orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/payment-status/{orderId}")
    public ResponseEntity<Void> updatePaymentStatus(
            @PathVariable Long orderId,
            @RequestBody PaymentStatusUpdateDto paymentStatus
            ){
        orderService.updatePaymentStatus(orderId, paymentStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
