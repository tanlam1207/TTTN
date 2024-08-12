package com.lamnhattan.example003.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.lamnhattan.example003.entity.ProductBrand;

public interface ProductBrandRepository extends CrudRepository<ProductBrand, UUID>{


    
}
