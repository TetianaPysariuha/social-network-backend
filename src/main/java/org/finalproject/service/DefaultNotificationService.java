package org.finalproject.service;


import lombok.RequiredArgsConstructor;
import org.finalproject.entity.Notification;
import org.finalproject.entity.Post;
import org.finalproject.entity.User;
import org.finalproject.repository.NotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor

public class DefaultNotificationService extends GeneralService<Notification> {

    private final NotificationRepository notificationRepository;

    public Page<Notification> findAllByUserId(Pageable pageable, User user) {
        return notificationRepository.findAllByReceiverContains(user, pageable);
    }
}
