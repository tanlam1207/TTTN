package com.lamnhattan.example003.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.Product;

public interface ProductRepository extends JpaRepository<Product, UUID>{
    
}
