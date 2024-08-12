package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Coupon;


public interface CouponService {
    Coupon createCoupon(Coupon Coupon);

    Coupon getCouponById(Long CouponId);

    List<Coupon> getAllCoupons();

    Coupon updateCoupon(Coupon Coupon);

    void deleteCoupon(Long CouponId);
}
