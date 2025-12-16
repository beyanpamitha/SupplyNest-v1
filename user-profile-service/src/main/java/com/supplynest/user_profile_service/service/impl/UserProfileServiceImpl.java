package com.supplynest.user_profile_service.service.impl;

import com.supplynest.user_profile_service.dto.PasswordUpdateDto;
import com.supplynest.user_profile_service.dto.UserProfileDto;
import com.supplynest.user_profile_service.dto.UserUpdateDto;
import com.supplynest.user_profile_service.entity.User;
import com.supplynest.user_profile_service.repository.UserProfileRepo;
import com.supplynest.user_profile_service.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepo userProfileRepo;
    private final ModelMapper modelMapper;

    @Override
    public UserProfileDto saveUser(UserProfileDto userProfileDto) {
        User user = modelMapper.map(userProfileDto,User.class);  //Converting user's data(Dto type) into Entity type, for save in the db.

        User saveUser = userProfileRepo.save(user);

        UserProfileDto savedUser = modelMapper.map(saveUser,UserProfileDto.class);  //Converting Entity type data into Dto type, for return to frontend.
        return savedUser;

    }

    @Override
    public UserProfileDto getUserById(Long id) {
        return null;
    }

    @Override
    public UserUpdateDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        return null;
    }

    @Override
    public String updatePassword(Long id, PasswordUpdateDto passwordUpdateDto) {
        return null;
    }

    @Override
    public UserProfileDto deleteUser(Long id) {
        return null;
    }
}
