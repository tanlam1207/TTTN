package com.lamnhattan.example003.entity;



import java.util.UUID;

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
@Table(name = "cart_items")
public class Card_item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    private Long id;

   
    @JoinColumn(name = "card_id", nullable = false)
    private Long card;

    // @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private UUID product_id;

    @Column(name = "quantity", nullable = false, length = 255)
    private int quantity;
    @Column(name = "price", nullable = false, length = 255)
    private double regular_price;
}