package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.ProductCoupon;
import com.lamnhattan.example003.repository.ProductCouponRepository;
import com.lamnhattan.example003.service.ProductCouponService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ProductCouponServiceImpl implements ProductCouponService {
    private ProductCouponRepository ProductCouponRepository;

    @Override
    public ProductCoupon createProductCoupon(ProductCoupon ProductCoupon) {
        return ProductCouponRepository.save(ProductCoupon);
    }

    @Override
    public ProductCoupon getProductCouponById(ProductCoupon.ProductCouponID ProductCouponId) {
        Optional<ProductCoupon> optionalProductCoupon = ProductCouponRepository.findById(ProductCouponId);
        return optionalProductCoupon.get();
    }

    @Override
    public List<ProductCoupon> getAllProductCoupons() {
        return ProductCouponRepository.findAll();
    }

    @Override
    public ProductCoupon updateProductCoupon(ProductCoupon ProductCoupon) {
        ProductCoupon existingProductCoupon = ProductCouponRepository.findById(ProductCoupon.getId()).get();
        existingProductCoupon.setCoupon(ProductCoupon.getCoupon());
        existingProductCoupon.setProduct(ProductCoupon.getProduct());
        ProductCoupon updatedProductCoupon = ProductCouponRepository.save(existingProductCoupon);
        return updatedProductCoupon;
    }

    @Override
    public void deleteProductCoupon(ProductCoupon.ProductCouponID ProductCouponId) {
        ProductCouponRepository.deleteById(ProductCouponId);
    }

}
