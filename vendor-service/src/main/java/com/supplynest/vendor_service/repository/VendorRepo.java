package com.supplynest.vendor_service.repository;

import com.supplynest.vendor_service.entity.VendorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepo extends JpaRepository<VendorProfile,Long> {
}
