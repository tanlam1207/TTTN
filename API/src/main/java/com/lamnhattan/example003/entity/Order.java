package com.lamnhattan.example003.entity;


import java.math.BigInteger;
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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    private Long id;


    @Column(name = "customer_id", nullable = false)
    private String customer_id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private Order_status orderStatus;
@Column(name = "first_name", nullable = false, length = 100)
    private String first_name;

    @Column(name = "last_name", nullable = false, length = 100)
    private String last_name;

    @Column(name = "phone_number", nullable = false, length = 100)
    private String phone_number;

    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Column(name = "address", nullable = false, length = 1000)
    private String address;
    
    @Column(name = "order_delivered_carrler_date", nullable = false, length = 255)
    private Date order_delivered_carrler_date;


    @Column(name = "created_at", nullable = false, length = 255)
    private Date created_at;

    @JsonIgnore
    @OneToMany(mappedBy = "order_id", cascade = CascadeType.REMOVE)
    private List<Order_item> orderItems;



   

}