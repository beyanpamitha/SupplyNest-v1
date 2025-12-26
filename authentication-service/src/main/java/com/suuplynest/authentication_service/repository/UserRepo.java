package com.suuplynest.authentication_service.repository;

import com.suuplynest.authentication_service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);
}
