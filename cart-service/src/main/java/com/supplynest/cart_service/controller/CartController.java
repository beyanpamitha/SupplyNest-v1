package com.supplynest.cart_service.controller;

import com.supplynest.cart_service.dto.AddItemRequestDto;
import com.supplynest.cart_service.dto.CartItemsDto;
import com.supplynest.cart_service.dto.CartsDto;
import com.supplynest.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/new-cart")
    public ResponseEntity<CartsDto> openNewCart(@RequestHeader("X-USER-ID") Long customerId){
        CartsDto newCart = cartService.openNewCart(customerId);
        return new ResponseEntity<>(newCart, HttpStatus.CREATED);
    }

    @GetMapping("/get-cart-details")
    public ResponseEntity<List<CartItemsDto>> getCartDetails(@RequestHeader("X-CUSTOMER-ID") Long customerId){
        return ResponseEntity.ok(
                cartService.getCartDetails(customerId)
        );
    }

    @PostMapping("add-item")
    public ResponseEntity<Void> addItemToCart(
            @RequestHeader("X-CUSTOMER-ID") Long customerId,
            @RequestBody AddItemRequestDto addItemRequestDto
    ){
        cartService.addItemToCart(customerId, addItemRequestDto.getProductId(), addItemRequestDto.getQuantity());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-item")
    public ResponseEntity<Void> deleteItem(
            @RequestHeader("X-CUSTOMER-ID") Long customerId,
            @PathVariable Long productId
    ){
        cartService.deleteItemFromCart(customerId,productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update-quantity")
    public ResponseEntity<Void> updateQuantity(
            @RequestHeader("X-CUSTOMER-ID") Long customerId,
            @PathVariable Long productId,
            @RequestParam Double quantity
    ){
        cartService.updateItemsQuantity(customerId,productId,quantity);
        return ResponseEntity.ok().build();
    }

}
