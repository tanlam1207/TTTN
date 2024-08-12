package com.lamnhattan.example003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long>{
    
}
