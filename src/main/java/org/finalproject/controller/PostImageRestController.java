package org.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.finalproject.entity.PostImage;
import org.finalproject.service.GeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postImages")
public class PostImageRestController {

    private final GeneralService<PostImage> postImageService;

    @GetMapping
    public List<PostImage> getAll() {


        return postImageService.findAll();

    }


    @GetMapping("/id")
    public ResponseEntity<?> getById(@PathVariable("id") Long postImageId) {

        PostImage postImage = postImageService.getOne(postImageId);
        if (postImage == null) {
            return ResponseEntity.badRequest().body("PostImage not found");
        }
        return ResponseEntity.ok().body(postImage);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePostImage(@RequestBody PostImage postImage) {

        try {
            postImageService.delete(postImage);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long postImageId) {

        try {
            postImageService.deleteById(postImageId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
