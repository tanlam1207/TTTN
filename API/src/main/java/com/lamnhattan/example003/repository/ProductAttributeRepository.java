package com.lamnhattan.example003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.ProductAttribute;
import com.lamnhattan.example003.entity.ProductAttribute.ProductAttributeID;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, ProductAttributeID>{
    
}
