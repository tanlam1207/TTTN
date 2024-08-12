package com.lamnhattan.example003.entity;


import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "galleries")
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "product_id") 
    private UUID productId; 

    @Column(name = "image_path", nullable = false, length = 255)
    private String image_path;

    @Column(name = "thumbnail", nullable = false, length = 255)
    private String thumbnail;

    @Column(name = "display_order", nullable = false, length = 255)
    private int display_order;

    @Column(name = "created_at", nullable = false, length = 255)
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "created_by")
    private StaffAccount createdBy;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "updated_by")
    private StaffAccount updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    @JsonIgnore
    private Product product;
}