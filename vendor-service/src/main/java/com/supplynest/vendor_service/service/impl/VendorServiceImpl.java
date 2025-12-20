package com.supplynest.vendor_service.service.impl;

import com.supplynest.vendor_service.dto.ActiveStatusUpdateDto;
import com.supplynest.vendor_service.dto.VendorProfileDto;
import com.supplynest.vendor_service.entity.VendorProfile;
import com.supplynest.vendor_service.repository.VendorRepo;
import com.supplynest.vendor_service.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepo vendorRepo;
    private final ModelMapper modelMapper;

    @Override
    public VendorProfileDto saveVendor(VendorProfileDto vendorProfileDto) {
        VendorProfile vendor = modelMapper.map(vendorProfileDto,VendorProfile.class);
        VendorProfile saveVendor = vendorRepo.save(vendor);
        return modelMapper.map(saveVendor,VendorProfileDto.class);
    }

    @Override
    public VendorProfileDto receiveVendor(Long id) {
        VendorProfile receivedVendor = vendorRepo.findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(
                                NOT_FOUND,
                                "User not found with id " + id
                        )
                );
        return modelMapper.map(receivedVendor,VendorProfileDto.class);
    }

    @Override
    public VendorProfileDto deleteVendor(Long id) {
        VendorProfile vendor = vendorRepo.findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(
                                NOT_FOUND,
                                "User not found with id " + id
                        )
                );
        vendorRepo.deleteById(id);
        return modelMapper.map(vendor,VendorProfileDto.class);
    }

    @Override
    public VendorProfileDto approveVendor(Long id, ActiveStatusUpdateDto activeStatusUpdateDto) {
        VendorProfile vendor = vendorRepo.findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(
                                NOT_FOUND,
                                "User not found with id " + id
                        )
                );
        if (activeStatusUpdateDto.getVendorStatus()!=null){
            vendor.setVendorStatus(activeStatusUpdateDto.getVendorStatus());
        }
        vendorRepo.save(vendor);
        return modelMapper.map(vendor, VendorProfileDto.class);
    }

}
