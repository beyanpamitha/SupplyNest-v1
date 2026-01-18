package com.supplynest.cart_service.repository;

import com.supplynest.cart_service.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CartItemsRepo extends JpaRepository<CartItems,Long> {
    List<CartItems> findByCartId(Long cartId);
}
