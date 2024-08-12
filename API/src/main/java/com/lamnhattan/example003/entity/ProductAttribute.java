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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_attributes")
public class ProductAttribute {
    @EmbeddedId
    private ProductAttributeID id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "attribute_id", nullable = false, insertable = false, updatable = false)
    private Attribute attribute;

    @Embeddable
    public static class ProductAttributeID implements Serializable {
        @Column(name = "product_id")
        private UUID productId;

        @Column(name = "attribute_id")
        private Long attributeId;
    }
}