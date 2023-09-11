package org.finalproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.finalproject.dto.post.PostDto;
import org.finalproject.dto.post.PostDtoMapper;
import org.finalproject.dto.post.PostRequestDto;
import org.finalproject.entity.Post;
import org.finalproject.service.DefaultPostService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostRestController {
    private final DefaultPostService postService;
    private final PostDtoMapper dtoMapper;
    private final PostDtoMapper postDtoMapper;


    @PostMapping("/comment/{id}")
    @MessageMapping("/comment")
    public Boolean commentPost(@PathVariable("id") Long postId, @RequestBody Map<String, String> requestBody) {
        String content = requestBody.get("content");
        return postService.commentPost(postId, content);
    }

    @PostMapping("/repost/{id}")
    @MessageMapping("/repost")
    public Boolean repostPost(@PathVariable("id") Long postId, @RequestBody Map<String, String> requestBody) {
        String content = requestBody.get("content");
        return postService.repostPost(postId, content);
    }

    @PutMapping("/remove-like-post/{id}")
    public Boolean removeLikePost(@PathVariable("id") Long postId) {
        return postService.removeLikePost(postId);
    }

    @PutMapping("/like-post/{id}")
    @MessageMapping("/add-like")
    public Boolean likePost(@PathVariable("id") Long postId) {
        return postService.likePost(postId);
    }

    @GetMapping
    public List<PostDto> getAll() {
        List<Post> postList = postService.findAllPosts();

        List<PostDto> postDtoList = postList.stream().map(postDtoMapper::decorateDto).collect(Collectors.toList());
        return postDtoList;
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<List<PostDto>> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        List<Post> postList =  postService.findAll(page, size);
        List<PostDto> postDtoList = postList.stream().map(postDtoMapper::decorateDto).collect(Collectors.toList());
        return ResponseEntity.ok(postDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long postId) {
        Post post = postService.getOne(postId);
        if (post == null) {
            return ResponseEntity.badRequest().body("Post not found");
        }
        return ResponseEntity.ok().body(postDtoMapper.convertToDto(post) );
    }

    @PostMapping
    public boolean create(@RequestParam("content") String content, @RequestParam(name = "files", required = false) List<MultipartFile> files) {
        return postService.create(content, files);
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
            Post postEntity = dtoMapper.convertToEntity(post);
            postEntity.setCreatedDate(postService.getOne(postEntity.getId()).getCreatedDate());
            postEntity.setCreatedBy(postService.getOne(postEntity.getId()).getCreatedBy());
            postService.save(postEntity);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
