package com.supplynest.cart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartsDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
