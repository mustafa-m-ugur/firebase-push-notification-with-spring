package com.firebasePushNotification.firebasePushNotification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String deviceToken;
}
