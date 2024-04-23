package com.firebasePushNotification.firebasePushNotification.dto;

import com.firebasePushNotification.firebasePushNotification.model.AccountModel;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {
    public AccountDTO convert(AccountModel account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setFirstName(account.getFirstName());
        accountDTO.setLastName(account.getLastName());
        accountDTO.setDeviceToken(account.getDeviceToken());
        return accountDTO;
    }
}
