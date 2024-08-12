package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.notification;
import com.lamnhattan.example003.repository.NotificationRepository;
import com.lamnhattan.example003.service.NotificationService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor

public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository NotificationRepository;

    @Override
    public notification createNotification(notification Notification) {
        return NotificationRepository.save(Notification);
    }

    @Override
    public notification getNotificationById(UUID NotificationId) {
        Optional<notification> optionalNotification = NotificationRepository.findById(NotificationId);
        return optionalNotification.get();
    }

    @Override
    public List<notification> getAllNotifications() {
        return NotificationRepository.findAll();
    }

    @Override
    public notification updateNotification(notification Notification) {
        notification existingNotification = NotificationRepository.findById(Notification.getId()).get();
        existingNotification.setStaffAccount(Notification.getStaffAccount());
        existingNotification.setTitle(Notification.getTitle());
        existingNotification.setContent(Notification.getContent());
        existingNotification.setSeen(Notification.getSeen());
        existingNotification.setCreatedAt(Notification.getCreatedAt());
        existingNotification.setReceivetime(Notification.getReceivetime());
        existingNotification.setNotification_expiry_date(Notification.getNotification_expiry_date());
        notification updatedNotification = NotificationRepository.save(existingNotification);
        return updatedNotification;
    }

    @Override
    public void deleteNotification(UUID NotificationId) {
        NotificationRepository.deleteById(NotificationId);
    }

}
