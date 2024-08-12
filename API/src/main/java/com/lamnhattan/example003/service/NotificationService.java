package com.lamnhattan.example003.service;

import java.util.List;
import java.util.UUID;

import com.lamnhattan.example003.entity.notification;


public interface NotificationService {
    notification createNotification(notification Notification);

    notification getNotificationById(UUID NotificationId);

    List<notification> getAllNotifications();

    notification updateNotification(notification Notification);

    void deleteNotification(UUID NotificationId);
}
