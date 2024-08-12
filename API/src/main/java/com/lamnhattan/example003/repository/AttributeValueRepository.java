package com.lamnhattan.example003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.AttributeValue;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long>{
    
}
