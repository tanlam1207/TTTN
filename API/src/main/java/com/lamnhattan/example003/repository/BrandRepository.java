package com.lamnhattan.example003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long>{
    
}

