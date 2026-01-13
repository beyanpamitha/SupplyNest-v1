package com.supplynest.catalog_service.service.impl;

import com.supplynest.catalog_service.dto.ProductPriceDto;
import com.supplynest.catalog_service.dto.ProductRequestDto;
import com.supplynest.catalog_service.dto.ProductsDto;
import com.supplynest.catalog_service.dto.VendorProfileDto;
import com.supplynest.catalog_service.entity.Category;
import com.supplynest.catalog_service.entity.Products;
import com.supplynest.catalog_service.repository.CategoryRepo;
import com.supplynest.catalog_service.repository.ProductsRepo;
import com.supplynest.catalog_service.service.CatalogService;
import com.supplynest.catalog_service.service.VendorClient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final ProductsRepo productsRepo;
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;
    private final VendorClient vendorClient;

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

    @Override
    public ProductsDto addProduct(ProductRequestDto productRequestDto, Long vendorId) {

        //Fetch Category from category entity
        Category category = categoryRepo.findByName(productRequestDto.getCategoryName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found: " + productRequestDto.getCategoryName()
                ));

        //Fetch Vendor id from vendor service
        VendorProfileDto vendor = vendorClient.getVendor(vendorId);

        //Check if product already exist in product db
        boolean exists = productsRepo.existsByProductNameAndCategory_IdAndVendorId(
                productRequestDto.getProductName(),
                category.getCategoryId(),
                vendorId
        );

        if (exists){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Product already added."
            );
        }

        //Mapping request dto into entity
        Products product = modelMapper.map(productRequestDto, Products.class);

        //Manually setting category and vendor id
        product.setCategory(category);
        product.setVendorName(vendor.getBusinessName());

        Products savedProduct = productsRepo.save(product);

        //Mapping saved entity to response dto
        ProductsDto responseDto = modelMapper.map(savedProduct, ProductsDto.class);

        //Setting category name as String in response dto
        responseDto.setCategory(savedProduct.getCategory().getName());

        return responseDto;
    }

    @Override
    public ProductsDto removeProduct(Long productId) {

        Products product = productsRepo.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found with id " + productId
                ));

        productsRepo.delete(product);
        return modelMapper.map(product, ProductsDto.class);
    }

    @Override
    public ProductsDto updateProduct(Long productId, ProductRequestDto productRequestDto) {

        Products product = productsRepo.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found with id " + productId
                ));

        //Configure ModelMapper to skip null values
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);

        //Mapping
        modelMapper.map(productRequestDto, product); //Here, don't map productRequestDto -> Products.class. It will create a new product in the entity. Instead of, map dto -> product(previously fetched product from entity).

        Products updatedProduct = productsRepo.save(product);
        return modelMapper.map(updatedProduct, ProductsDto.class);
    }

    @Override
    public ProductsDto updateUnitPrice(Long productId, Double unitPrice) {

        Products product = productsRepo.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found with id " + productId
                ));

        if (unitPrice == null || unitPrice <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Unit price must be greater than zero"
            );
        }

        product.setUnitPrice(unitPrice); //Didn't use modelmapper here, because there are only one field to update.
        Products updatedProduct = productsRepo.save(product);

        return modelMapper.map(updatedProduct, ProductsDto.class);
    }

    @Override
    public ProductsDto getProductById(Long productId) {

        Products product = productsRepo.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found with id " + productId
                ));

        return modelMapper.map(product, ProductsDto.class);
    }
}
