package com.supplynest.vendor_service.controller;

import com.supplynest.vendor_service.dto.ActiveStatusUpdateDto;
import com.supplynest.vendor_service.dto.VendorProfileDto;
import com.supplynest.vendor_service.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vendor-profile")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    //Creating a vendor
    @PostMapping("save-vendor")
    public ResponseEntity<VendorProfileDto> saveVendor (@RequestBody VendorProfileDto vendorProfileDto){
        VendorProfileDto savedVendor = vendorService.saveVendor(vendorProfileDto);
        return new ResponseEntity<>(savedVendor, HttpStatus.CREATED);
    }

    //Receiving a vendor
    @GetMapping("{id}")
    public ResponseEntity<VendorProfileDto> getVendor (@PathVariable Long id){
        VendorProfileDto receivedVendor = vendorService.receiveVendor(id);
        return new ResponseEntity<>(receivedVendor, HttpStatus.OK);
    }

    //Deleting a vendor
    @DeleteMapping("{id}")
    public ResponseEntity<VendorProfileDto> deleteVendor(@PathVariable Long id){
        VendorProfileDto deletedVendor = vendorService.deleteVendor(id);
        return new ResponseEntity<>(deletedVendor, HttpStatus.OK);
    }

    //Vendor approval management
    @PutMapping("{id}")
    public ResponseEntity<VendorProfileDto> vendorApproval (
            @PathVariable Long id,
            @RequestBody ActiveStatusUpdateDto activeStatusUpdateDto
    ){
        VendorProfileDto approvedVendor = vendorService.approveVendor(id, activeStatusUpdateDto);
        return new ResponseEntity<>(approvedVendor, HttpStatus.OK);
    }
}
