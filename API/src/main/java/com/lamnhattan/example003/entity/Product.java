package com.lamnhattan.example003.entity;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id", nullable = false, unique = true)
private UUID product_id;

    @Column(name = "product_name",nullable = false, length = 255)
    private String product_name;

    @Column(name = "sku")
    private String sku;

    @Column(name = "regular_price")
    private double regular_price;

    @Column(name = "discount_price")
    private double discount_price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "short_description" )
    private String short_description;

    @Column(name = "product_description",length = 1000)
    private String product_description;

    @Column(name = "product_weight")
    private double product_weight;

    @Column(name = "product_note")
    private String product_note;

    @Column(name = "published")
    private boolean published;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "created_by")
    private UUID created_by;

    @Column(name = "updated_by")
    private UUID updated_by;

    // @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductCategories> productCategories;
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Product_tags> Producttags;
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Gallery> galleries;
    // @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductBrand> brands;

}
