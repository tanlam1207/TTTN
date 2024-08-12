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
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 255)
    private String first_name;

    @Column(name = "last_name", nullable = false, length = 255)
    private String last_name;

    @Column(name = "phone_number", nullable = false, length = 255)
    private String phone_number;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String password_hash;

    @Column(name = "active", nullable = false, length = 255)
    private String active;

    @Column(name = "registered_at", nullable = false, length = 255)
    private Date registered_at;
    
    @Column(name = "created_at", nullable = false, length = 255)
    private Date created_at;


    // @JsonIgnore
    // @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    // private List<Order> orders;

    // @JsonIgnore
    // @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    // private List<Card> cards;

   

}