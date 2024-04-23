package com.firebasePushNotification.firebasePushNotification.repositories;

import com.firebasePushNotification.firebasePushNotification.model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationModel, Long> {
}
