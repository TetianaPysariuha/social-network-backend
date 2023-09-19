package org.finalproject.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.*;
import org.finalproject.dto.chat.ChatDto;
import org.finalproject.entity.Notification;
import org.finalproject.entity.User;
import org.finalproject.entity.UserImage;
import org.finalproject.service.DefaultNotificationService;
import org.finalproject.service.DefaultUserService;
import org.finalproject.service.GeneralService;
import org.finalproject.util.NotificationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final DefaultNotificationService notificationService;

    private final DefaultNotificationService defaultNotificationService;

    private final NotificationDtoMapper dtoMapper;

    private final DefaultUserService userService;

    @GetMapping
    public List<NotificationDto> getAll() {


        return notificationService.findAll()
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());

    }

    @GetMapping("/{page}/{size}")

    public ResponseEntity<List<NotificationDto>> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = PageRequest.of(page, size, sort);
        List<NotificationDto> notificationDtoList = notificationService.findAll(pageable).toList()
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(notificationDtoList);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAuthorizedUserNotifications() {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> profile = userService.getByFullName(auth);

        if (profile.isEmpty()) {
            throw new EntityNotFoundException();
        }
        List<NotificationDto> notificationsDto = profile.get().getNotifications()
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(notificationsDto);
    }

    @GetMapping("/user/size")
    public ResponseEntity<?> getAuthorizedUserNotificationsSize() {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> profile = userService.getByFullName(auth);

        if (profile.isEmpty()) {
            throw new EntityNotFoundException();
        }
        List<Notification> newNotifications = profile.get().getNotifications()
                .stream()
                .filter(notification -> NotificationStatus.pending.equals(notification.getStatus()))
                .collect(Collectors.toList());
        System.out.println(newNotifications);
        return ResponseEntity.ok().body(newNotifications.size());
    }

    @GetMapping("/user/{page}/{size}")
    public ResponseEntity<?> getAuthorizedUserNotificationPageable(@PathVariable Integer page, @PathVariable Integer size, @RequestParam("status") String status) {
        System.out.println(status);
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> profile = userService.getByFullName(auth);

        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(page, size, sort);
        if (profile.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Page<Notification> notifications  = defaultNotificationService.findAuthUserNotifications(profile.get(), status, pageable);
        List<NotificationDto> notificationsDto = notifications
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(notificationsDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody NotificationRequestDto notification) {
        try {

            Notification notificationEntity = dtoMapper.convertToEntity(notification);
            notificationEntity.setCreatedDate(notificationService.getOne(notification.getId()).getCreatedDate());
            notificationService.save(notificationEntity);


            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id) {
        try {

            Notification notificationEntity = notificationService.getOne(id);
            notificationEntity.setStatus(NotificationStatus.viewed);
            notificationService.save(notificationEntity);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/id")
    public ResponseEntity<?> getById(@PathVariable("id") Long notificationId) {

        Notification notification = notificationService.getOne(notificationId);
        if (notification == null) {
            return ResponseEntity.badRequest().body("MessageImage not found");
        }
        return ResponseEntity.ok().body(notification);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteNotification(@RequestBody NotificationRequestDto notification) {

        try {
            notificationService.delete(dtoMapper.convertToEntity(notification));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long notificationId) {

        try {
            notificationService.deleteById(notificationId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
