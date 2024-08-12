package com.lamnhattan.example003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
