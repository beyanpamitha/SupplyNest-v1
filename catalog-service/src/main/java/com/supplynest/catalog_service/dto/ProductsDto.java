package com.supplynest.catalog_service.dto;

import com.supplynest.catalog_service.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductsDto {

    private Long productId;
    private String vendorName;
    private String category;   //In the response DTO, we expose only the category name as a String. This avoids exposing the internal JPA entity, simplifies the API, and ensures security and decoupling between the API layer and the database.
    private String productName;
    private String description;
    private Double unitPrice;
    private Double availableStock;
    private Integer minStockThreshold;
    private Boolean activeStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
