package org.finalproject.dto;

import org.finalproject.dto.friend.FriendDto;
import org.finalproject.dto.friend.FriendRequestDto;
import org.finalproject.entity.Friend;
import org.finalproject.entity.Notification;
import org.finalproject.facade.GeneralFacade;
import org.finalproject.repository.NotificationRepository;
import org.finalproject.repository.UserJpaRepository;
import org.finalproject.service.DefaultFriendService;
import org.finalproject.service.DefaultNotificationService;
import org.finalproject.util.FriendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationDtoMapper extends GeneralFacade<Notification, NotificationRequestDto, NotificationDto> {

    @Autowired
    private DefaultNotificationService defaultNotificationService;
    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    protected void decorateDto(NotificationDto dto, Notification entity) {

        dto.setType(entity.getType().getValue());
        dto.setStatus(entity.getStatus().getValue());

    }
}

