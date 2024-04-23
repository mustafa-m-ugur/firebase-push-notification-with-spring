package com.firebasePushNotification.firebasePushNotification.service.impl;

import com.firebasePushNotification.firebasePushNotification.dto.AccountDTO;
import com.firebasePushNotification.firebasePushNotification.dto.AccountDtoConverter;
import com.firebasePushNotification.firebasePushNotification.model.AccountModel;
import com.firebasePushNotification.firebasePushNotification.repositories.AccountRepository;
import com.firebasePushNotification.firebasePushNotification.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultAccountService implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountDtoConverter accountDtoConverter;
    @Override
    public List<AccountDTO> getAccountList() {
        List<AccountModel> accountModelList = accountRepository.findAll();
        return accountModelList.stream().map(account -> accountDtoConverter.convert(account)).toList();
    }

    @Override
    public AccountDTO getAccount(Long id) {
        Optional<AccountModel> accountModel = accountRepository.findById(id);
        return accountModel.map(accountDtoConverter::convert).orElse(new AccountDTO());
    }

    @Override
    public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {
        Optional<AccountModel> optionalAccountModel = accountRepository.findById(id);
        optionalAccountModel.ifPresent(accountModel -> {
            accountModel.setEmail(accountDTO.getEmail());
            accountModel.setFirstName(accountDTO.getFirstName());
            accountModel.setLastName(accountDTO.getLastName());
            accountModel.setPassword(accountDTO.getPassword());
            accountModel.setDeviceToken(accountDTO.getDeviceToken());
            accountRepository.save(accountModel);
        });

        return optionalAccountModel.map(accountDtoConverter::convert).orElse(new AccountDTO());
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        AccountModel accountModel = new AccountModel();
        accountModel.setEmail(accountDTO.getEmail());
        accountModel.setFirstName(accountDTO.getFirstName());
        accountModel.setLastName(accountDTO.getLastName());
        accountModel.setPassword(accountDTO.getPassword());
        accountModel.setDeviceToken(accountDTO.getDeviceToken());
        AccountModel account = accountRepository.save(accountModel);
        return accountDtoConverter.convert(account);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
