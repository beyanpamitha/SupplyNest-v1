package com.supplynest.notification_service.controller;

import com.supplynest.notification_service.dto.InventoryNotificationRequestDto;
import com.supplynest.notification_service.dto.OrderNotificationRequestDto;
import com.supplynest.notification_service.dto.PaymentNotificationRequestDto;
import com.supplynest.notification_service.entity.Notifications;
import com.supplynest.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/order")
    public ResponseEntity<Void> orderNotification(@RequestBody OrderNotificationRequestDto orderNotificationRequestDto) {
        notificationService.handleOrderNotification(orderNotificationRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/payment")
    public ResponseEntity<Void> paymentNotification(@RequestBody PaymentNotificationRequestDto paymentNotificationRequestDto) {
        notificationService.handlePaymentNotification(paymentNotificationRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/inventory")
    public ResponseEntity<Void> inventoryNotification(@RequestBody InventoryNotificationRequestDto inventoryNotificationRequestDto) {
        notificationService.handleInventoryNotification(inventoryNotificationRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Notifications>> getUserNotification(@PathVariable Long userId) {
        List<Notifications> notifications = notificationService.getNotificationByUser(userId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @PutMapping("/read/{id}")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
