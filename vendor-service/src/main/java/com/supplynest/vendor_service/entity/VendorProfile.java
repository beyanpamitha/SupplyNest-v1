package com.supplynest.vendor_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_profile")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VendorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String businessName;
    private String businessAddress;
    @Column(nullable = false)
    private String contactNumber;
    @Column(nullable = false)
    private String taxId;
    private String vendorStatus;
    private Double rating;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
