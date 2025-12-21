package com.supplynest.cart_service.repository;

import com.supplynest.cart_service.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Carts,Long> {
    Optional<Carts> findByCustomerId(Long customerId);
}
