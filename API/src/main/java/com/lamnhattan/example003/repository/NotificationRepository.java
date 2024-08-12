package com.lamnhattan.example003.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.notification;

public interface NotificationRepository extends JpaRepository<notification, UUID>{
    
}
