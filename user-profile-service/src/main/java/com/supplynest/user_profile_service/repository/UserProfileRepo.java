package com.supplynest.user_profile_service.repository;

import com.supplynest.user_profile_service.dto.UserUpdateDto;
import com.supplynest.user_profile_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepo extends JpaRepository<User,Long> {
}
