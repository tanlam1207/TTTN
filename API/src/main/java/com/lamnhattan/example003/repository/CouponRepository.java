package com.lamnhattan.example003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
    
}
