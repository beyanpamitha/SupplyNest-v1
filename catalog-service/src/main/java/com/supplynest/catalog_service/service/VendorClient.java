package com.supplynest.catalog_service.service;

import com.supplynest.catalog_service.dto.VendorProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8108", value = "VENDOR-SERVICE")
public interface VendorClient {

    @GetMapping("/api/v1/vendor-profile/{id}")
    VendorProfileDto getVendor(@PathVariable("id") Long vendorId);
}
