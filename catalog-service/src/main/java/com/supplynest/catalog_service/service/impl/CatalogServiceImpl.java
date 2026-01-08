package com.supplynest.catalog_service.service.impl;

import com.supplynest.catalog_service.dto.ProductPriceDto;
import com.supplynest.catalog_service.entity.Products;
import com.supplynest.catalog_service.repository.ProductsRepo;
import com.supplynest.catalog_service.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final ProductsRepo productsRepo;
    private final ModelMapper modelMapper;

    @Override
    public ProductPriceDto getItemUnitPrice(Long productId) {

        //Fetch product or throw 404 if not found
        Products products = productsRepo.findById(productId)
                .orElseThrow(()->
                        new ResponseStatusException(
                                NOT_FOUND,
                                "Product not found with id: " + productId
                        )
                );
        //Map entity → DTO
        ProductPriceDto itemPrice = modelMapper.map(products, ProductPriceDto.class);
        return itemPrice;
    }
}
