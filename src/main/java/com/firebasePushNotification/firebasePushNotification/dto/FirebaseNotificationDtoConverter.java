package com.firebasePushNotification.firebasePushNotification.dto;

import org.springframework.stereotype.Component;

@Component
public class FirebaseNotificationDtoConverter {
    public FirebaseNotificationDto convert(NotificationDTO notificationDTO) {
        FirebaseNotificationDto target = new FirebaseNotificationDto();
        target.setTitle(notificationDTO.getTitle());
        target.setBody(notificationDTO.getDescription());
        target.setName(notificationDTO.getAccount().getFirstName() + " " + notificationDTO.getAccount().getLastName());
        target.setDa("this is console data");
        return target;
    }
}
