package org.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.PostDto;
import org.finalproject.dto.PostDtoMapper;
import org.finalproject.dto.PostRequestDto;
import org.finalproject.entity.Post;
import org.finalproject.entity.PostTypes;
import org.finalproject.entity.User;
import org.finalproject.service.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostRestController {
    private final GeneralService<Post> postService;
    private final PostDtoMapper dtoMapper;

    @GetMapping
    public List<PostDto> getAll() {
        // return userService.findAll().stream().map(dtoMapper::convertToDto).collect(Collectors.toList());
        List<Post> postList = postService.findAll();
        List<PostDto> postDtoList = postList.stream().map(dtoMapper::convertToDto).collect(Collectors.toList());
        // return userList;
        return postDtoList;
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Sort sort =  Sort.by(new Sort.Order(Sort.Direction.ASC,"id"));
        Pageable pageable = PageRequest.of(page,size,sort);
        Page posts = postService.findAll(pageable);
        List<Post> postList =  posts.toList();
        List<PostDto> userDtoList = postList.stream().map(dtoMapper::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long postId) {
        Post post = postService.getOne(postId);
        if (post == null) {
            return ResponseEntity.badRequest().body("Post not found");
        }
        return ResponseEntity.ok().body(dtoMapper.convertToDto(post) );
    }

    @PostMapping
    public void create(@RequestBody PostRequestDto post) {
        postService.save(dtoMapper.convertToEntity(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long postId) {
        try {
            postService.deleteById(postId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PostRequestDto post) {
        try {
            postService.save(dtoMapper.convertToEntity(post));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
