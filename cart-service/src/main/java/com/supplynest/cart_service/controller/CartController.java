package com.supplynest.cart_service.controller;

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

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CartItemsDto>> getCartDetails(@PathVariable Long customerId){
        return ResponseEntity.ok(
                cartService.getCartDetails(customerId)
        );
    }

    @PostMapping("/{productId}")
    public ResponseEntity<List<CartItemsDto>> addItemToCart(
            @PathVariable Long productId,
            @RequestBody Double quantity
    ){
        return ResponseEntity.ok(
                cartService.addItemToCart(productId,quantity)
        );
        //Service method is completed. Complete it
    }
}
