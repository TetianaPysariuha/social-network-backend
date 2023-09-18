package org.finalproject.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.*;
import org.finalproject.entity.Notification;
import org.finalproject.entity.User;
import org.finalproject.entity.UserImage;
import org.finalproject.service.DefaultUserImageService;
import org.finalproject.service.DefaultUserService;
import org.finalproject.service.GeneralService;
import org.springframework.data.domain.Page;
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
@RequestMapping("/userImages")
public class UserImageController {

    private final GeneralService<UserImage> userImageService;

    private final UserImageDtoMapper dtoMapper;

    private final DefaultUserService defaultUserService;

    private final DefaultUserImageService defaultUserImageService;

    @GetMapping
    public List<UserImageDto> getAll() {

        return userImageService.findAll()
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());

    }

    @GetMapping("/{page}/{size}")

    public ResponseEntity<List<UserImageDto>> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = PageRequest.of(page, size, sort);
        List<UserImageDto> userImageDtoList = userImageService.findAll(pageable).toList()
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userImageDtoList);
    }

    @GetMapping("/user/{page}/{size}")
    public ResponseEntity<?> getAuthorizedUserImagesPageable(@PathVariable Integer page, @PathVariable Integer size) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> profile = defaultUserService.getByFullName(auth);
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = PageRequest.of(page, size, sort);
        if (profile.isEmpty()) {
            throw new EntityNotFoundException();
        }
        Page<UserImage> images  = defaultUserImageService.findAuthUserImagesPageable(profile.get().getId(),pageable);
        List<UserImageDto> userImageDto = images
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());


        return ResponseEntity.ok().body(userImageDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserImageDtoRequest image) {
        try {

            UserImage imageEntity = userImageService.getOne(image.getId());
             imageEntity.setImageUrl(image.getImageUrl());
             userImageService.save(imageEntity);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long userImageId) {

       UserImage userImage = userImageService.getOne(userImageId);
        if (userImage == null) {
           throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().body(userImage);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserImage(@RequestBody UserImageDtoRequest userImage) {

            userImageService.delete(dtoMapper.convertToEntity(userImage));
            return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long userImageId) {

            userImageService.deleteById(userImageId);
            return ResponseEntity.ok().build();

    }



}
