package com.supplynest.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentNotificationRequestDto {
    private Long orderId;
    private Long userId;
    private boolean paymentSuccess;
}
