package com.suuplynest.authentication_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDto {

    private Long roleId;
    private String role;
    private String roleDescription;
}
