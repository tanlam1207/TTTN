package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.Coupon;
import com.lamnhattan.example003.service.CouponService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/coupons")
public class CouponController {
    
    private CouponService CouponService;

    // Create Coupon rest API
    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon Coupon) {
        Coupon savedCoupon = CouponService.createCoupon(Coupon);
        return new ResponseEntity<>(savedCoupon, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/Coupon/1
    @GetMapping("{id}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable("id") Long CouponId) {
        Coupon Coupon = CouponService.getCouponById(CouponId);
        return new ResponseEntity<>(Coupon, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/Coupon
    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        List<Coupon> Coupons = CouponService.getAllCoupons();
        return new ResponseEntity<>(Coupons, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/Coupon/1
    @PutMapping("{id}")
    public ResponseEntity<Coupon> updateCoupon(@PathVariable("id") Long CouponId,
            @RequestBody Coupon Coupon) {
        Coupon.setId(CouponId);
        Coupon updateCoupon = CouponService.updateCoupon(Coupon);
        return new ResponseEntity<>(updateCoupon, HttpStatus.OK);
    }

    // Delete Coupon REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable("id") Long CouponId) {
        CouponService.deleteCoupon(CouponId);
        return new ResponseEntity<>("Coupon successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}