package com.supplynest.user_profile_service.service;

import com.supplynest.user_profile_service.dto.PasswordUpdateDto;
import com.supplynest.user_profile_service.dto.UserProfileDto;
import com.supplynest.user_profile_service.dto.UserUpdateDto;

public interface UserProfileService {

    UserProfileDto getUserById(Long id);

    UserUpdateDto updateUser(Long id, UserUpdateDto userUpdateDto);

    String updatePassword(Long id, PasswordUpdateDto passwordUpdateDto);

    UserProfileDto deleteUser(Long id);
}
