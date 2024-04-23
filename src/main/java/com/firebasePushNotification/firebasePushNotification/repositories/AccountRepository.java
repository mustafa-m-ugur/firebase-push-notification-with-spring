package com.firebasePushNotification.firebasePushNotification.repositories;

import com.firebasePushNotification.firebasePushNotification.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountModel, Long> {
}
