package com.firebasePushNotification.firebasePushNotification.controller;

import com.firebasePushNotification.firebasePushNotification.dto.NotificationDTO;
import com.firebasePushNotification.firebasePushNotification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/")
    public List<NotificationDTO> getNotificationList() {
        return notificationService.getNotificationList();
    }

    @GetMapping("/{id}")
    public NotificationDTO getNotification(@PathVariable Long id) {
        return notificationService.getNotification(id);
    }

    @PutMapping("/{id}")
    public NotificationDTO updateNotification(@PathVariable Long id, @RequestBody NotificationDTO notificationDTO) {
        return notificationService.updateNotification(id, notificationDTO);
    }

    @PostMapping("/")
    public NotificationDTO createNotification(@RequestBody NotificationDTO notificationDTO) {
        return notificationService.createNotification(notificationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }

    @PostMapping("/sendNotification")
    public Object sendNotification(@PathVariable Long id, @RequestParam String deviceToken) throws IOException, InterruptedException {
        return notificationService.sendNotification(id, List.of(deviceToken));
    }

    @PostMapping("/sendAllAccountNotification")
    public Object sendAllAccountNotification(@PathVariable Long id) throws IOException, InterruptedException {
        return notificationService.sendAllAccountNotification(id);
    }
}
