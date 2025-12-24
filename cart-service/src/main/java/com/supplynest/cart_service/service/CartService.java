package com.supplynest.cart_service.service;

import com.supplynest.cart_service.dto.CartItemsDto;
import com.supplynest.cart_service.dto.CartsDto;

import java.util.List;

public interface CartService {
    List<CartItemsDto> getCartDetails(Long customerId);

    CartsDto openNewCart(CartsDto cartsDto);

    List<CartItemsDto> addItemToCart(Long productId, Double quantity);
}
