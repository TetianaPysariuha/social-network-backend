package org.finalproject.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.*;
import org.finalproject.entity.ImgComment;
import org.finalproject.entity.User;
import org.finalproject.entity.UserImage;
import org.finalproject.service.GeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final GeneralService<ImgComment> commentService;

    private final GeneralService<UserImage> imageService;

    private final ImgCommentDtoMapper dtoMapper;

    @GetMapping
    public List<ImgCommentDto> getAll() {


        return commentService.findAll().stream().map(dtoMapper::convertToDto).collect(Collectors.toList());

    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ImgCommentDtoRequest comment) {
        try {

            ImgComment commentEntity = commentService.getOne(comment.getId());
            commentEntity.setContent(comment.getContent());
            commentService.save(commentEntity);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ImgCommentDtoRequest comment) {

            ImgComment commentEntity = dtoMapper.convertToEntity(comment);
            commentService.save(commentEntity);

            return ResponseEntity.ok().build();

    }

    @GetMapping("/image/{imageId}")
    public ResponseEntity<?> getUserImageComments(@PathVariable ("imageId") Long imageId) {

        UserImage image = imageService.getOne(imageId);

        if (image == null) {
           return ResponseEntity.badRequest().body("Image not found");
        }
        List<ImgCommentDto> commentsDto = image.getComments()
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(commentsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long commentId) {

        ImgComment comment = commentService.getOne(commentId);
        if (comment == null) {
            return ResponseEntity.badRequest().body("Comment not found");
        }
        return ResponseEntity.ok().body(dtoMapper.convertToDto(comment));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMessageImage(@RequestBody ImgCommentDtoRequest commentDtoRequest) {


           commentService.delete(dtoMapper.convertToEntity(commentDtoRequest));
            return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long commentId) {


            commentService.deleteById(commentId);
            return ResponseEntity.ok().build();

    }


}
