package org.finalproject.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.UserImageDtoMapper;
import org.finalproject.dto.UserImageDtoRequest;
import org.finalproject.entity.UserImage;
import org.finalproject.service.GeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/userImages")
public class UserImageController {

    private final GeneralService<UserImage> userImageService;

    private final UserImageDtoMapper dtoMapper;

    @GetMapping
    public List<UserImage> getAll() {

        return userImageService.findAll();

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
    public ResponseEntity<?> deleteUserImage(@RequestBody UserImage userImage) {

            userImageService.delete(userImage);
            return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long userImageId) {

            userImageService.deleteById(userImageId);
            return ResponseEntity.ok().build();

    }



}
