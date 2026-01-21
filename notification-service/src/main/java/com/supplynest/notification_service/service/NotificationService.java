package com.supplynest.notification_service.service;

import com.supplynest.notification_service.dto.InventoryNotificationRequestDto;
import com.supplynest.notification_service.dto.OrderNotificationRequestDto;
import com.supplynest.notification_service.dto.PaymentNotificationRequestDto;
import com.supplynest.notification_service.entity.Notifications;

import java.util.List;

public interface NotificationService {
    void handleOrderNotification(OrderNotificationRequestDto orderNotificationRequestDto);

    void handlePaymentNotification(PaymentNotificationRequestDto paymentNotificationRequestDto);

    void handleInventoryNotification(InventoryNotificationRequestDto inventoryNotificationRequestDto);

    List<Notifications> getNotificationByUser(Long userId);

    void markAsRead(Long id);
}
