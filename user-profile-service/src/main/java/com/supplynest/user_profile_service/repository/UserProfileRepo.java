package com.supplynest.user_profile_service.repository;

import com.supplynest.user_profile_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<User,Long> {
}
