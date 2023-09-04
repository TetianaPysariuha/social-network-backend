package org.finalproject.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.*;
import org.finalproject.dto.chat.ChatDto;
import org.finalproject.entity.Notification;
import org.finalproject.entity.User;
import org.finalproject.entity.UserImage;
import org.finalproject.service.DefaultUserService;
import org.finalproject.service.GeneralService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private final GeneralService<Notification> notificationService;

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

    @GetMapping("/user/{page}/{size}")
    public ResponseEntity<?> getAuthorizedUserNotificationsPageAble(@PathVariable Integer page, @PathVariable Integer size) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> profile = userService.getByFullName(auth);
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "createdDate"));
        Pageable pageable = PageRequest.of(page, size, sort);

        if (profile.isEmpty()) {
            throw new EntityNotFoundException();
        }
        List<NotificationDto> notificationsDto = profile.get().getNotifications()
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(notificationsDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody NotificationRequestDto notification) {
        try {

            Notification notificationEntity = notificationService.getOne(notification.getId());
            notificationEntity.getCreatedDate();
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
