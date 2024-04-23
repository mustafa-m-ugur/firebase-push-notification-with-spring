package com.firebasePushNotification.firebasePushNotification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Long id;
    private String title;
    private String description;
    private Boolean isRead;
    private Long accountId;
    private AccountDTO account;
}
