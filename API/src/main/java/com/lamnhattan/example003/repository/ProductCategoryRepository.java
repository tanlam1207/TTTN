package com.lamnhattan.example003.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.lamnhattan.example003.entity.ProductCategories;

public interface ProductCategoryRepository extends CrudRepository<ProductCategories, UUID>{
    
}
