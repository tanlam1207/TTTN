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
@Table(name = "order_statuses")
public class Order_status {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    private Long id;

    @Column(name = "status_name", nullable = false, length = 255)
    private String status_name;

    @Column(name = "color", nullable = false, length = 255)
    private String color;

    @Column(name = "privacy", nullable = false, length = 255)
    private String privacy;

    @Column(name = "created_at", nullable = false, length = 255)
    private Date created_at;

    @Column(name = "updated_at", nullable = false, length = 255)
    private Date updated_at;

    @Column(name = "created_by", nullable = false, length = 255)
    private Date created_by;

    @Column(name = "updated_by", nullable = false, length = 255)
    private Date updated_by;

      @JsonIgnore
    @OneToMany(mappedBy = "orderStatus", cascade = CascadeType.REMOVE)
    private List<Order> orders;


}