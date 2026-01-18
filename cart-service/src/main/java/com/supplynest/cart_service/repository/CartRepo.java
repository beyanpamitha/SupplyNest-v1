package com.supplynest.cart_service.repository;

import com.supplynest.cart_service.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CartRepo extends JpaRepository<Carts,Long> {
    Optional<Carts> findByCustomerId(Long customerId);
}
