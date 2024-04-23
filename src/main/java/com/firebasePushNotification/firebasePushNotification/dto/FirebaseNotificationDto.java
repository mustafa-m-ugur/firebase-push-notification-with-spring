package com.firebasePushNotification.firebasePushNotification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseNotificationDto {
    private String title;
    private String body;
    private String name;
    private String da;
    private String clickUrl;
}
