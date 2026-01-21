package com.supplynest.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderNotificationRequestDto {
    private Long orderId;
    private Long userId;
    private Long vendorId;
    private String orderStatus; //Created,Cancelled
}
