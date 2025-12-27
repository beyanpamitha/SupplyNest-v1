package com.suuplynest.authentication_service.service.impl;

import com.suuplynest.authentication_service.dto.RoleDto;
import com.suuplynest.authentication_service.entity.Roles;
import com.suuplynest.authentication_service.repository.RoleRepo;
import com.suuplynest.authentication_service.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final ModelMapper modelMapper;
    private final RoleRepo roleRepo;

    @Override
    public RoleDto createNewRole(RoleDto roleDto) {
        Roles newRole = modelMapper.map(roleDto, Roles.class);
        Roles createdRole = roleRepo.save(newRole);
        return modelMapper.map(createdRole, RoleDto.class);
    }
}
