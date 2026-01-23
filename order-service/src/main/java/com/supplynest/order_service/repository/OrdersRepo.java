package com.supplynest.order_service.repository;

import com.supplynest.order_service.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrdersRepo extends JpaRepository<Orders,Long> {
}
