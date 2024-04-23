package com.firebasePushNotification.firebasePushNotification.service.impl;

import com.firebasePushNotification.firebasePushNotification.dto.AccountDTO;
import com.firebasePushNotification.firebasePushNotification.dto.NotificationDTO;
import com.firebasePushNotification.firebasePushNotification.dto.NotificationDtoConverter;
import com.firebasePushNotification.firebasePushNotification.model.AccountModel;
import com.firebasePushNotification.firebasePushNotification.model.NotificationModel;
import com.firebasePushNotification.firebasePushNotification.provider.firebase.Firebase;
import com.firebasePushNotification.firebasePushNotification.repositories.NotificationRepository;
import com.firebasePushNotification.firebasePushNotification.service.AccountService;
import com.firebasePushNotification.firebasePushNotification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultNotificationService implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationDtoConverter notificationDtoConverter;

    @Autowired
    private Firebase firebase;

    @Autowired
    private AccountService accountService;

    @Override
    public List<NotificationDTO> getNotificationList() {
        List<NotificationModel> NotificationModelList = notificationRepository.findAll();
        return NotificationModelList.stream().map(account -> notificationDtoConverter.convert(account)).toList();
    }

    @Override
    public NotificationDTO getNotification(Long id) {
        Optional<NotificationModel> notificationModel = notificationRepository.findById(id);
        return notificationModel.map(notificationDtoConverter::convert).orElse(new NotificationDTO());
    }

    @Override
    public NotificationDTO updateNotification(Long id, NotificationDTO notificationDTO) {
        Optional<NotificationModel> optionalNotificationModel = notificationRepository.findById(id);
        optionalNotificationModel.ifPresent(notificationModel -> {
            notificationModel.setTitle(notificationDTO.getTitle());
            notificationModel.setDescription(notificationDTO.getDescription());
            //notificationModel.setIsRead(false);
            notificationRepository.save(notificationModel);
        });

        return optionalNotificationModel.map(notificationDtoConverter::convert).orElse(new NotificationDTO());
    }

    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setTitle(notificationDTO.getTitle());
        notificationModel.setDescription(notificationDTO.getDescription());
        notificationModel.setIsRead(false);
        notificationModel.setAccountId(notificationDTO.getAccountId());
        NotificationModel notification = notificationRepository.save(notificationModel);
        return notificationDtoConverter.convert(notification);
    }

    @Override
    public Object sendNotification(Long id, List<String> deviceTokenList) throws IOException, InterruptedException {
        NotificationDTO notification = this.getNotification(id);
        return firebase.sendNotification(notification, deviceTokenList);
    }

    @Override
    public Object sendAllAccountNotification(Long id) throws IOException, InterruptedException {
        List<AccountDTO> accountList = accountService.getAccountList();
        List<String> deviceTokenList = accountList.stream().map(AccountDTO::getDeviceToken).toList();
        return sendNotification(id, deviceTokenList);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
