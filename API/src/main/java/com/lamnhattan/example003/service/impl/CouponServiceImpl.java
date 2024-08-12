package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Attribute;
import com.lamnhattan.example003.entity.Coupon;
import com.lamnhattan.example003.repository.CouponRepository;
import com.lamnhattan.example003.service.CouponService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class CouponServiceImpl implements CouponService {
    private CouponRepository CouponRepository;

    @Override
    public Coupon createCoupon(Coupon Coupon) {
        return CouponRepository.save(Coupon);
    }

    @Override
    public Coupon getCouponById(Long CouponId) {
        Optional<Coupon> optionalCoupon = CouponRepository.findById(CouponId);
        return optionalCoupon.get();
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return CouponRepository.findAll();
    }

    @Override
    public Coupon updateCoupon(Coupon Coupon) {
        Coupon existingCoupon = CouponRepository.findById(Coupon.getId()).get();
        existingCoupon.setCode(Coupon.getCode());
        existingCoupon.setCoupon_description(Coupon.getCoupon_description());
        existingCoupon.setDiscount_value(Coupon.getDiscount_value());
        existingCoupon.setDiscount_type(Coupon.getDiscount_type());
        existingCoupon.setTimes_used(Coupon.getTimes_used());
        existingCoupon.setMax_usage(Coupon.getMax_usage());
        existingCoupon.setCoupon_start_date(Coupon.getCoupon_start_date());
        existingCoupon.setCoupon_end_date(Coupon.getCoupon_end_date());
        existingCoupon.setCreated_at(Coupon.getCreated_at());
        existingCoupon.setUpdated_at(Coupon.getUpdated_at());
        existingCoupon.setCreatedBy(Coupon.getCreatedBy());
        existingCoupon.setUpdatedBy(Coupon.getUpdatedBy());

        Coupon updatedCoupon = CouponRepository.save(existingCoupon);
        return updatedCoupon;
    }

    @Override
    public void deleteCoupon(Long CouponId) {
        CouponRepository.deleteById(CouponId);
    }

}
