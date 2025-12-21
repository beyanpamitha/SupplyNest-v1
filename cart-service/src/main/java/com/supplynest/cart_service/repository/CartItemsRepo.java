package com.supplynest.cart_service.repository;

import com.supplynest.cart_service.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemsRepo extends JpaRepository<CartItems,Long> {
    List<CartItems> findByCartId(Long cartId);
}
