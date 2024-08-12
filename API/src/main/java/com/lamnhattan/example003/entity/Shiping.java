package com.lamnhattan.example003.entity;

import java.util.Date;
import java.util.List;

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
@Table(name = "shippings")
public class Shiping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "active", nullable = false, length = 255)
    private String active;

    @Column(name = "icon_path", nullable = false, length = 255)
    private String icon_path;

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
    @OneToMany(mappedBy = "shipping", cascade = CascadeType.REMOVE)
    private List<ProductShipping> productShippings;

    // @JsonIgnore
    // @OneToMany(mappedBy = "shipping", cascade = CascadeType.REMOVE)
    // private List<Order_item> orderItems;



}