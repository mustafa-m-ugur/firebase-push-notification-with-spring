package com.firebasePushNotification.firebasePushNotification.service;

import com.firebasePushNotification.firebasePushNotification.dto.AccountDTO;

import java.util.List;

public interface AccountService {

    List<AccountDTO> getAccountList();
    AccountDTO getAccount(Long id);
    AccountDTO updateAccount(Long id, AccountDTO accountDTO);
    AccountDTO createAccount(AccountDTO accountDTO);
    void deleteAccount(Long id);
}
