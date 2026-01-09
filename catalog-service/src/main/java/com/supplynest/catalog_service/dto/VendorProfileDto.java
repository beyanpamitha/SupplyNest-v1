package com.supplynest.catalog_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VendorProfileDto {
    private Long id;
    private Long userId;
    private String businessName;
    private String businessAddress;
    private String contactNumber;
    private String taxId;
    private String vendorStatus;
    private Double rating;
    private LocalDateTime createdAt;
}
