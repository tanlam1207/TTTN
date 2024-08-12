package com.lamnhattan.example003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.Sell;

public interface SellRepository extends JpaRepository<Sell, Long>{
    
}
