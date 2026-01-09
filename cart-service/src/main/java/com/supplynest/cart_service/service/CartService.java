package com.supplynest.cart_service.service;

import com.supplynest.cart_service.dto.CartItemsDto;
import com.supplynest.cart_service.dto.CartsDto;

import java.util.List;

public interface CartService {
    List<CartItemsDto> getCartDetails(Long customerId);

    CartsDto openNewCart(Long customerId);

    void addItemToCart(Long customerId, Long productId, Double quantity);

    void deleteItemFromCart(Long customerId, Long productId);

    void updateItemsQuantity(Long customerId, Long productId, Double quantity);
}
