package com.lamnhattan.example003.entity;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

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
@Table(name = "notifications")
public class notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    
@ManyToOne
    @JoinColumn(name = "account_id")
    private StaffAccount staffAccount;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "seen", nullable = false)
    private Boolean seen;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Column(name = "receive_time", nullable = false)
    private Instant receivetime;
    @Column(name = "notification_expiry_date", nullable = false)
    private Date notification_expiry_date;

 
}
