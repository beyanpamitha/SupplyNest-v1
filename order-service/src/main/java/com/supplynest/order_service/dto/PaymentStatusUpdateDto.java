package com.supplynest.order_service.dto;

import com.supplynest.order_service.entity.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentStatusUpdateDto {
    private PaymentStatus paymentStatus;
}
