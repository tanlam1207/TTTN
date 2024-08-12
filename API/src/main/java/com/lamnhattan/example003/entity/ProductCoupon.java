package com.lamnhattan.example003.entity;
import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_coupons")
public class ProductCoupon {
    @EmbeddedId
    private ProductCouponID id;

    @ManyToOne
    @JoinColumn(name = "coupon_id", nullable = false, insertable = false, updatable = false)
    private Coupon coupon;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;

    @Embeddable
    public static class ProductCouponID implements Serializable {
        @Column(name = "coupon_id")
        private Long coupon_id;

        @Column(name = "product_id")
        private UUID product_id;
        
    }
}