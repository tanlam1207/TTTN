package com.lamnhattan.example003.entity;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    private Long id;

    @Column(name = "code", nullable = false, length = 255)
    private String code;

    @Column(name = "coupon_description", nullable = false, length = 255)
    private String coupon_description;

    @Column(name = "discount_value", nullable = false, length = 255)
    private double discount_value;

    @Column(name = "discount_type", nullable = false, length = 255)
    private double discount_type;

    @Column(name = "times_used", nullable = false, length = 255)
    private int times_used;

    @Column(name = "max_usage", nullable = false, length = 255)
    private int max_usage;

    @Column(name = "coupon_start_date", nullable = false, length = 255)
    private Date coupon_start_date;

    @Column(name = "coupon_end_date", nullable = false, length = 255)
    private Date coupon_end_date;

    @Column(name = "created_at", nullable = false, length = 255)
    private Date created_at;

    @Column(name = "updated_at", nullable = false, length = 255)
    private Date updated_at;

        @ManyToOne
        @JoinColumn(name = "created_by")
        private StaffAccount createdBy;

        @ManyToOne
        @JoinColumn(name = "updated_by")
        private StaffAccount updatedBy;

    @JsonIgnore
    @OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE)
    private List<ProductCoupon> productCoupons;


}