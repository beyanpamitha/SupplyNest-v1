package com.supplynest.catalog_service.repository;

import com.supplynest.catalog_service.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Products, Long> {
}
