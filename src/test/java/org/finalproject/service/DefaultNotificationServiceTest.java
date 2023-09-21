package org.finalproject.service;


import org.finalproject.entity.Notification;
import org.finalproject.entity.User;
import org.finalproject.repository.NotificationRepository;
import org.finalproject.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultNotificationServiceTest {

    @Mock
    private NotificationRepository notificationTestJpaRepository;


    @InjectMocks
    private GeneralService<Notification> notificationService  = new GeneralService<Notification>() {
    };

    @InjectMocks
    private DefaultNotificationService defaultNotificationService;

    @Captor
    private ArgumentCaptor<Notification> notificationArgumentCaptor;

    @Test
    public void testGetAllPageble() {
        Notification notification = new Notification();
        when(notificationTestJpaRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(notification)));
        Pageable pageable = PageRequest.of(1, 2);
        List<Notification> notifications = notificationService.findAll(pageable).toList();

        assertEquals(notification, notifications.get(0));
    }

    @Test
    public void test_GetAll_Success() {
        Notification notification1 = new Notification();
        Notification notification2 = new Notification();
        List<Notification> notificationsExpected = List.of(notification1, notification2);
        when(notificationTestJpaRepository.findAll())
                .thenReturn(notificationsExpected);

        List<Notification> notificationsActual = notificationService.findAll();
        assertNotNull(notificationsActual);
        assertFalse(notificationsActual.isEmpty());
        assertIterableEquals(notificationsExpected, notificationsActual);
    }

    @Test
    public void test_Create_Success() {
        Notification notification1 = new Notification();

        notificationService.save(notification1);

        verify(notificationTestJpaRepository).save(notificationArgumentCaptor.capture());
        Notification notificationActualArgument = notificationArgumentCaptor.getValue();
        assertEquals(notification1,notificationActualArgument);
    }
    @Test
    public void test_Put_Success() {
        Notification notification1 = new Notification();


        notification1.setContent(" New message from Jane Birkin");
        notification1.setCreatedDate(new Date());

        notificationService.save(notification1);

        verify(notificationTestJpaRepository).save(notificationArgumentCaptor.capture());
        Notification  notificationActualArgument = notificationArgumentCaptor.getValue();
        assertEquals(notification1, notificationActualArgument);
    }
    @Test
    public void test_Delete_Success() {
        Notification notification1 = new Notification();

        notificationService.save(notification1);
        notification1.setId(1L);

        notification1.setContent(" New message from Jane Birkin");
        notificationService.delete(notification1);

        verify(notificationTestJpaRepository).delete(notificationArgumentCaptor.capture());
        Notification notificationActualArgument = notificationArgumentCaptor.getValue();
        assertEquals(notification1, notificationActualArgument);
    }


}