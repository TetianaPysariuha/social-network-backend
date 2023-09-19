package org.finalproject.service;


import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Notification;
import org.finalproject.entity.Post;
import org.finalproject.entity.User;
import org.finalproject.repository.NotificationRepository;
import org.finalproject.util.NotificationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class DefaultNotificationService extends GeneralService<Notification> {

    private final NotificationRepository notificationRepository;


    public Page<Notification> findAuthUserNotifications(User user, String status, Pageable pageable) {
        if(status.equals("all")) {
            return notificationRepository.findAllByReceiverContains(user, pageable);
        } else if (status.equals("pending")) {
            return notificationRepository.findAllByReceiverAndStatus(user, NotificationStatus.pending , pageable);
        }
        throw new IllegalArgumentException();
//        return null;
    }
}
