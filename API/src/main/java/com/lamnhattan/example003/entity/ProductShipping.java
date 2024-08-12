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
@Table(name = "product_shippings")
public class ProductShipping {
    @EmbeddedId
    private ProductShippingID id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shipping_id", nullable = false, insertable = false, updatable = false)
    private Shiping shipping;

    @Column(name = "ship_charge", nullable = false, length = 255)
    private int ship_charge;

    @Column(name = "free", nullable = false, length = 255)
    private Boolean free;

    @Column(name = "estimated_days", nullable = false, length = 255)
    private int estimated_days;

    @Embeddable
    public static class ProductShippingID implements Serializable {
        @Column(name = "product_id")
        private UUID product_id;

        @Column(name = "shipping_id")
        private Long shipping_id;

    }
}