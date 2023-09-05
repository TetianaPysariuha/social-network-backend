package org.finalproject.service;


import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Notification;
import org.finalproject.entity.User;
import org.finalproject.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor

public class DefaultNotificationService extends GeneralService<Notification> {

    private final NotificationRepository notificationRepository;
}
