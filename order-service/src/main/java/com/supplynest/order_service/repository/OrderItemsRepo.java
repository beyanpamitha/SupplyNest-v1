package com.supplynest.order_service.repository;

import com.supplynest.order_service.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderItemsRepo extends JpaRepository<OrderItems,Long> {
}
