package com.lamnhattan.example003.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamnhattan.example003.entity.notification;
import com.lamnhattan.example003.service.NotificationService;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},exposedHeaders = "Content-Range")
@RestController
@AllArgsConstructor
@RequestMapping("api/notifications")
public class NotificationController {
    
    private NotificationService NotificationService;

    // Create Notification rest API
    @PostMapping
    public ResponseEntity<notification> createNotification(@RequestBody notification Notification) {
        notification savedNotification = NotificationService.createNotification(Notification);
        return new ResponseEntity<>(savedNotification, HttpStatus.CREATED);
    }

    // Get product by id REST API
    // http://localhost:8080/api/Notification/1
    @GetMapping("{id}")
    public ResponseEntity<notification> getNotificationById(@PathVariable("id") UUID NotificationId) {
        notification Notification = NotificationService.getNotificationById(NotificationId);
        return new ResponseEntity<>(Notification, HttpStatus.OK);
    }

    // Get all product REST API
    // http://localhost:8080/api/Notification
    @GetMapping
    public ResponseEntity<List<notification>> getAllNotifications() {
        List<notification> Notifications = NotificationService.getAllNotifications();
        return new ResponseEntity<>(Notifications, HttpStatus.OK);
    }

    // Update product REST API
    // http://localhost:8080/api/Notification/1
    @PutMapping("{id}")
    public ResponseEntity<notification> updateNotification(@PathVariable("id") UUID NotificationId,
            @RequestBody notification Notification) {
        Notification.setId(NotificationId);
        notification updateNotification = NotificationService.updateNotification(Notification);
        return new ResponseEntity<>(updateNotification, HttpStatus.OK);
    }

    // Delete Notification REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable("id") UUID NotificationId) {
        NotificationService.deleteNotification(NotificationId);
        return new ResponseEntity<>("Notification successfully deleted!", HttpStatus.OK);
    }
    // Get All Products REST API with Pagination
   
}