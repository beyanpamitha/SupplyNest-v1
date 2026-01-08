package com.supplynest.catalog_service.repository;

import com.supplynest.catalog_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
