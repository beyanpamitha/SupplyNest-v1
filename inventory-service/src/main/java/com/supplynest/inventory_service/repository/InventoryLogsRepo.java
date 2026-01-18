package com.supplynest.inventory_service.repository;

import com.supplynest.inventory_service.entity.InventoryLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface InventoryLogsRepo extends JpaRepository<InventoryLogs, Long> {
}
