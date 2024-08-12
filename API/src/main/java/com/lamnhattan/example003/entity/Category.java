package com.lamnhattan.example003.entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;


import java.time.Instant;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id", nullable = false, unique = true)
    private UUID categoryId;

    @Column(name = "parent_id")
    private UUID parentId;

    @Column(name = "category_name", nullable = false, length = 255)
    private String name;

    @Column(name = "category_description")
    private String description;

    @Column(name = "icon")
    private String icon;

    @Column(name = "image_path")
    private String image;

    @Column(name = "active")
    private Boolean active;

        @Column(name = "created_at", nullable = false,updatable = false)
        private Instant created_at;


        @Column(name = "updated_at")
        private Instant updated_at;

        @ManyToOne
        @JoinColumn(name = "created_by")
        private StaffAccount createdBy;

        @ManyToOne
        @JoinColumn(name = "updated_by")
        private StaffAccount updatedBy;
   @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private Category parentsId;

    @JsonIgnore
    @OneToMany(mappedBy = "parentsId", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Category> parents_Id;
    @JsonIgnore
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ProductCategories> Productcategories;

}