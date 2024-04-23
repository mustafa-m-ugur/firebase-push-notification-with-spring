package com.firebasePushNotification.firebasePushNotification.dto;

import com.firebasePushNotification.firebasePushNotification.model.NotificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationDtoConverter {
    @Autowired
    private AccountDtoConverter accountDtoConverter;
    public NotificationDTO convert(NotificationModel notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(notification.getId());
        notificationDTO.setTitle(notification.getTitle());
        notificationDTO.setDescription(notification.getDescription());
        notificationDTO.setIsRead(notification.getIsRead());
        if (notification.getAccountId() != null) {
            notificationDTO.setAccountId(notification.getAccountId());
            notificationDTO.setAccount(accountDtoConverter.convert(notification.getAccount()));
        }

        return notificationDTO;
    }
}
