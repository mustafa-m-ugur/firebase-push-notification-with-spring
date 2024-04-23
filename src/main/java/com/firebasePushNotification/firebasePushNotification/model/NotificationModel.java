package com.firebasePushNotification.firebasePushNotification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "account_id")
    private Long accountId;

    @OneToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private AccountModel account;

}
