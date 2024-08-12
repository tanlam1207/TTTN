package com.lamnhattan.example003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.Order_status;

public interface OrderStatusRepository extends JpaRepository<Order_status, Long>{
    
}
