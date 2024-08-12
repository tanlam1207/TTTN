package com.lamnhattan.example003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
}
