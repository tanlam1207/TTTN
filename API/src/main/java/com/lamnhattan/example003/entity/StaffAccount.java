package com.lamnhattan.example003.entity;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;

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
@Table(name = "staff_accounts")
public class StaffAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String first_name;

    @Column(name = "last_name", nullable = false, length = 100)
    private String last_name;

    @Column(name = "phone_number", nullable = false, length = 100)
    private String phone_number;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password_hash;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "profile_img")
    private String profile_img;

    @Column(name = "registered_at", nullable = false)
    private Instant registered_at;

    @Column(name = "updated_at", nullable = true)
    private Instant updated_at;
    @JsonIgnore
    @OneToMany(mappedBy = "staffAccount", cascade = CascadeType.ALL)
    private List<Staff_roles> Staffroles;
    @JsonIgnore
    @OneToMany(mappedBy = "staffAccount", cascade = CascadeType.ALL)
    private List<notification> notifications;

    // Các getter và setter


}