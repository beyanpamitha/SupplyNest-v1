package com.suuplynest.authentication_service.service;

import com.suuplynest.authentication_service.dto.RoleDto;

public interface RoleService {
    RoleDto createNewRole(RoleDto roleDto);
}
