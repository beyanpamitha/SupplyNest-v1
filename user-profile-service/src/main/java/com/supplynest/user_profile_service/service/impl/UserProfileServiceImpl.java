package com.supplynest.user_profile_service.service.impl;

import com.supplynest.user_profile_service.dto.PasswordUpdateDto;
import com.supplynest.user_profile_service.dto.UserProfileDto;
import com.supplynest.user_profile_service.dto.UserUpdateDto;
import com.supplynest.user_profile_service.service.UserProfileService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Override
    public UserProfileDto saveUser(UserProfileDto userProfileDto) {
        return null;
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
    public void updatePassword(Long id, PasswordUpdateDto passwordUpdateDto) {

    }

    @Override
    public UserProfileDto deleteUser(Long id) {
        return null;
    }
}
