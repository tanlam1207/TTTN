package com.lamnhattan.example003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.ProductCoupon;
import com.lamnhattan.example003.entity.ProductCoupon.ProductCouponID;

public interface ProductCouponRepository extends JpaRepository<ProductCoupon, ProductCouponID>{
    
}
