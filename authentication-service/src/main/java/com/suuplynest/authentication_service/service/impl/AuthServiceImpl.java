package com.suuplynest.authentication_service.service.impl;

import com.suuplynest.authentication_service.dto.UsersDto;
import com.suuplynest.authentication_service.entity.Users;
import com.suuplynest.authentication_service.repository.UserRepo;
import com.suuplynest.authentication_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Override
    public UsersDto saveUser(UsersDto usersDto) {

        Users user = modelMapper.map(usersDto,Users.class);  //Converting user's data(Dto type) into Entity type, for save in the db.

        Users saveUser = userRepo.save(user);

        UsersDto savedUser = modelMapper.map(saveUser,UsersDto.class);  //Converting Entity type data into Dto type, for return to frontend.
        return savedUser;

    }
}
