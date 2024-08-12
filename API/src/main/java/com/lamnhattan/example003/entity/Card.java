package com.lamnhattan.example003.entity;


import java.math.BigInteger;
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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", nullable = false, length = 11)
    private Long card_id;

    @Column(name = "google_customer_id")
    private BigInteger googlecustomer;

    @JsonIgnore
    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<Card_item> cardItems;

    // @JsonIgnore
    // @ManyToOne
    // @JoinColumn(name = "customer_id")
    // private Customer customer;
}