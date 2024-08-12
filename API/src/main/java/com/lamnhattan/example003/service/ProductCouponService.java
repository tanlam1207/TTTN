package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.ProductCoupon;


public interface ProductCouponService {
    ProductCoupon createProductCoupon(ProductCoupon ProductCoupon);

    ProductCoupon getProductCouponById(ProductCoupon.ProductCouponID ProductCouponId);

    List<ProductCoupon> getAllProductCoupons();

    ProductCoupon updateProductCoupon(ProductCoupon ProductCoupon);

    void deleteProductCoupon(ProductCoupon.ProductCouponID ProductCouponId);
}
