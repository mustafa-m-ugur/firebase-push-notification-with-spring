package com.firebasePushNotification.firebasePushNotification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseDTO {
    private List<String> registrationIds;
    private FirebaseNotificationDto notification;
}
