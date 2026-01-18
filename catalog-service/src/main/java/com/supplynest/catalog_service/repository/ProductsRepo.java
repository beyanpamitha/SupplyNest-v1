package com.supplynest.catalog_service.repository;

import com.supplynest.catalog_service.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ProductsRepo extends JpaRepository<Products, Long> {
    //Check if a product with the same name, category, and vendor exists
    boolean existsByProductNameAndCategory_IdAndVendorId(String productName, Long categoryId, Long vendorId);
}
