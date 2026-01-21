package com.supplynest.notification_service.service.impl;

import com.supplynest.notification_service.dto.InventoryNotificationRequestDto;
import com.supplynest.notification_service.dto.OrderNotificationRequestDto;
import com.supplynest.notification_service.dto.PaymentNotificationRequestDto;
import com.supplynest.notification_service.entity.Notifications;
import com.supplynest.notification_service.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void handleOrderNotification(OrderNotificationRequestDto orderNotificationRequestDto) {

    }

    @Override
    public void handlePaymentNotification(PaymentNotificationRequestDto paymentNotificationRequestDto) {

    }

    @Override
    public void handleInventoryNotification(InventoryNotificationRequestDto inventoryNotificationRequestDto) {

    }

    @Override
    public List<Notifications> getNotificationByUser(Long userId) {
        return List.of();
    }

    @Override
    public void markAsRead(Long id) {

    }
}
