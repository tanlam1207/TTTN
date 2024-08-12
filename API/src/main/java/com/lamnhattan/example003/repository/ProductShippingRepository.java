package com.lamnhattan.example003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.ProductShipping;
import com.lamnhattan.example003.entity.ProductShipping.ProductShippingID;

public interface ProductShippingRepository extends JpaRepository<ProductShipping, ProductShippingID>{
    
}
