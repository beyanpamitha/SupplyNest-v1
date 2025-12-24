package com.suuplynest.authentication_service.service;

import com.suuplynest.authentication_service.dto.UsersDto;

public interface AuthService {
    UsersDto saveUser(UsersDto usersDto);
}
