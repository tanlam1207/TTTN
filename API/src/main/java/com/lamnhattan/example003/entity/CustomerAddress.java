package com.lamnhattan.example003.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "customer_addresses")
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "address_line1", nullable = false, length = 255)
    private String address_line1;

    @Column(name = "address_line2", nullable = false, length = 255)
    private String address_line2;

    @Column(name = "postal_code", nullable = false, length = 255)
    private String postal_code;

    @Column(name = "country", nullable = false, length = 255)
    private String country;

    @Column(name = "city", nullable = false, length = 255)
    private String city;

    @Column(name = "phone_number", nullable = false, length = 255)
    private String phone_number;

}