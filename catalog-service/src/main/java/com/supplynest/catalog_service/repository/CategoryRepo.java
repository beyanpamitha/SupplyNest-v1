package com.supplynest.catalog_service.repository;

import com.supplynest.catalog_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CategoryRepo extends JpaRepository<Category, Long> {
    // Find category by name
    Optional<Category> findByName(String name);
}