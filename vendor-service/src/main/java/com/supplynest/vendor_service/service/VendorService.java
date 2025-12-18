package com.supplynest.vendor_service.service;

import com.supplynest.vendor_service.dto.VendorProfileDto;

public interface VendorService {
    VendorProfileDto saveVendor(VendorProfileDto vendorProfileDto);

    VendorProfileDto deleteVendor(Long id);

    VendorProfileDto approveVendor(Long id);

    VendorProfileDto receiveVendor(Long id);
}
