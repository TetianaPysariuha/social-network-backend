package org.finalproject.service;


import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Notification;
import org.finalproject.entity.User;
import org.finalproject.repository.NotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class DefaultNotificationService extends GeneralService<Notification> {

    private final NotificationRepository notificationRepository;

    public Page<Notification> findAuthUserNotifications(Long id, Pageable pageable) {

       return  notificationRepository.findAuthUserNotifications(id,pageable);
    }
}
