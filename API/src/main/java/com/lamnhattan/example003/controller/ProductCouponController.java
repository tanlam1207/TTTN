package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.ProductCoupon;
import com.lamnhattan.example003.service.ProductCouponService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/productCoupons")
public class ProductCouponController {
    
    private ProductCouponService ProductCouponService;

    // Create ProductCoupon rest API
    @PostMapping
    public ResponseEntity<ProductCoupon> createProductCoupon(@RequestBody ProductCoupon ProductCoupon) {
        ProductCoupon savedProductCoupon = ProductCouponService.createProductCoupon(ProductCoupon);
        return new ResponseEntity<>(savedProductCoupon, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/ProductCoupon/1
    @GetMapping("{id}")
    public ResponseEntity<ProductCoupon> getProductCouponById(@PathVariable("id") ProductCoupon.ProductCouponID ProductCouponId) {
        ProductCoupon ProductCoupon = ProductCouponService.getProductCouponById(ProductCouponId);
        return new ResponseEntity<>(ProductCoupon, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/ProductCoupon
    @GetMapping
    public ResponseEntity<List<ProductCoupon>> getAllProductCoupons() {
        List<ProductCoupon> ProductCoupons = ProductCouponService.getAllProductCoupons();
        return new ResponseEntity<>(ProductCoupons, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/ProductCoupon/1
    @PutMapping("{id}")
    public ResponseEntity<ProductCoupon> updateProductCoupon(@PathVariable("id") ProductCoupon.ProductCouponID ProductCouponId,
            @RequestBody ProductCoupon ProductCoupon) {
        ProductCoupon.setId(ProductCouponId);
        ProductCoupon updateProductCoupon = ProductCouponService.updateProductCoupon(ProductCoupon);
        return new ResponseEntity<>(updateProductCoupon, HttpStatus.OK);
    }

    // Delete ProductCoupon REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductCoupon(@PathVariable("id") ProductCoupon.ProductCouponID ProductCouponId) {
        ProductCouponService.deleteProductCoupon(ProductCouponId);
        return new ResponseEntity<>("ProductCoupon successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}