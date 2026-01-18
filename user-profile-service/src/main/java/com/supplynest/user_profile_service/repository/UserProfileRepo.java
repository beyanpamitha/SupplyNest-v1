package com.supplynest.user_profile_service.repository;

import com.supplynest.user_profile_service.dto.UserUpdateDto;
import com.supplynest.user_profile_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserProfileRepo extends JpaRepository<User,Long> {
}
