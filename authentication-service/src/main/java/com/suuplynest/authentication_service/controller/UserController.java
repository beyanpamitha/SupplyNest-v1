package com.suuplynest.authentication_service.controller;

import com.suuplynest.authentication_service.dto.UsersDto;
import com.suuplynest.authentication_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("register-new-user")
    public ResponseEntity<UsersDto> saveUser(@RequestBody UsersDto usersDto){
        UsersDto savedUser = authService.saveUser(usersDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("admin-acc")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminDashboard(){
        return "This is Admin dashboard";
    }

    @GetMapping("customer-acc")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String getCustomerDashboard(){
        return "This is Customer dashboard";
    }

    @GetMapping("vendor-acc")
    @PreAuthorize("hasRole('VENDOR')")
    public String getVendorDashboard(){
        return "This is Vendor dashboard";
    }
}
