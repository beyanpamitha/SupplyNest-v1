package com.supplynest.notification_service.repository;

import com.supplynest.notification_service.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface NotificationsRepo extends JpaRepository<Notifications,Long> {
}
