package org.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.finalproject.entity.UserImage;
import org.finalproject.service.GeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/userImages")
public class UserImageRestController {

    private final GeneralService<UserImage> userImageService;

    @GetMapping
    public List<UserImage> getAll() {


        return userImageService.findAll();

    }


    @GetMapping("/id")
    public ResponseEntity<?> getById(@PathVariable("id") Long userImageId) {

       UserImage userImage = userImageService.getOne(userImageId);
        if (userImage == null) {
            return ResponseEntity.badRequest().body("MessageImage not found");
        }
        return ResponseEntity.ok().body(userImage);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMessageImage(@RequestBody UserImage userImage) {

        try {
            userImageService.delete(userImage);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long userImageId) {

        try {
            userImageService.deleteById(userImageId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
