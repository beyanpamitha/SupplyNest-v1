package com.supplynest.cart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemsDto {

    private Long id;
    private Long cartId;
    private Long productId;
    private Double quantity;
    private Double unitPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
