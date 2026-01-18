package com.suuplynest.authentication_service.repository;

import com.suuplynest.authentication_service.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RoleRepo extends JpaRepository<Roles,Long> {
}
