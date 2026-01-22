package com.supplynest.notification_service.service.impl;

import com.supplynest.notification_service.dto.InventoryNotificationRequestDto;
import com.supplynest.notification_service.dto.OrderNotificationRequestDto;
import com.supplynest.notification_service.dto.PaymentNotificationRequestDto;
import com.supplynest.notification_service.entity.Notifications;
import com.supplynest.notification_service.entity.enums.Channel;
import com.supplynest.notification_service.entity.enums.RecipientType;
import com.supplynest.notification_service.entity.enums.Status;
import com.supplynest.notification_service.repository.NotificationsRepo;
import com.supplynest.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationsRepo notificationsRepo;
    private final ModelMapper modelMapper;

    @Override
    public void handleOrderNotification(OrderNotificationRequestDto orderNotificationRequestDto) {

        //Handle customer notifications
        Notifications customerNotifications = new Notifications();
        customerNotifications.setUserId(orderNotificationRequestDto.getUserId());
        customerNotifications.setRecipientType(RecipientType.CUSTOMER);
        customerNotifications.setChannel(Channel.IN_APP);
        customerNotifications.setStatus(Status.SENT);

        if ("CREATED".equalsIgnoreCase(orderNotificationRequestDto.getOrderStatus())){
            customerNotifications.setTitle("Order confirmed");
            customerNotifications.setMessage(
                    "Your order #" + orderNotificationRequestDto.getOrderId() + "has been placed successfully"
            );
        } else if ("CANCELLED".equalsIgnoreCase(orderNotificationRequestDto.getOrderStatus())) {
            customerNotifications.setTitle("Order cancelled");
            customerNotifications.setMessage(
                    "Your order #" + orderNotificationRequestDto.getOrderId() + "has been cancelled"
            );
        }

        notificationsRepo.save(customerNotifications);

        //Vendor notifications
        Notifications vendorNotifications = new Notifications();
        vendorNotifications.setUserId(orderNotificationRequestDto.getVendorId());
        vendorNotifications.setRecipientType(RecipientType.VENDOR);
        vendorNotifications.setChannel(Channel.IN_APP);
        vendorNotifications.setStatus(Status.SENT);

        if ("CREATED".equalsIgnoreCase(orderNotificationRequestDto.getOrderStatus())){
            vendorNotifications.setTitle("New order received");
            vendorNotifications.setMessage(
                    "You have received a new order #" +orderNotificationRequestDto.getOrderId()
            );
        } else if ("CANCELLED".equalsIgnoreCase(orderNotificationRequestDto.getOrderStatus())) {
            vendorNotifications.setTitle("Order cancelled");
            vendorNotifications.setMessage(
                    "Order #" + orderNotificationRequestDto.getOrderId() + "has been cancelled by the customer"
            );
        }

        notificationsRepo.save(vendorNotifications);

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
