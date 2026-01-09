package com.supplynest.catalog_service.dto;

import com.supplynest.catalog_service.entity.Products;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    private Long categoryId;
    private String name;
    private String description;
    private List<Products> products;
    private LocalDateTime createdAt;
}
