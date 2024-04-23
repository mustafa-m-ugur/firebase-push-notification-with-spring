package com.firebasePushNotification.firebasePushNotification.service;


import com.firebasePushNotification.firebasePushNotification.dto.NotificationDTO;

import java.io.IOException;
import java.util.List;

public interface NotificationService {
    List<NotificationDTO> getNotificationList();
    NotificationDTO getNotification(Long id);
    NotificationDTO updateNotification(Long id, NotificationDTO accountDTO);
    NotificationDTO createNotification(NotificationDTO accountDTO);
    Object sendNotification(Long id, List<String> deviceTokenList) throws IOException, InterruptedException;
    Object sendAllAccountNotification(Long id) throws IOException, InterruptedException;
    void deleteNotification(Long id);
}
