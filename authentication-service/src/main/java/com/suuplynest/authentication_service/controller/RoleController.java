package com.suuplynest.authentication_service.controller;

import com.suuplynest.authentication_service.dto.RoleDto;
import com.suuplynest.authentication_service.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/new-role")
    public ResponseEntity<RoleDto> createNewRole(@RequestBody RoleDto roleDto){
        RoleDto newRole = roleService.createNewRole(roleDto);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }
}
