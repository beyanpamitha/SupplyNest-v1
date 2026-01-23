package com.supplynest.notification_service.repository;

import com.supplynest.notification_service.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface NotificationsRepo extends JpaRepository<Notifications,Long> {
    List<Notifications> findByUserIdOrderByCreatedAtDesc(Long userId);
}
