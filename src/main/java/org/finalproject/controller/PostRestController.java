package org.finalproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.finalproject.config.AuditorAwareImpl;
import org.finalproject.dto.PostDto;
import org.finalproject.dto.PostDtoMapper;
import org.finalproject.dto.PostRequestDto;
import org.finalproject.entity.Post;
import org.finalproject.entity.PostImage;
import org.finalproject.entity.User;
import org.finalproject.service.DefaultPostImageService;
import org.finalproject.service.DefaultPostService;
import org.finalproject.service.DefaultUserService;
import org.finalproject.service.FileUpload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
    private final DefaultUserService userService;
    private final AuditorAwareImpl auditorAwareImpl;

    private final PostDtoMapper postDtoMapper;

    private final FileUpload fileUpload;
    private final DefaultPostImageService postImageService;


    @PostMapping("/comment/{id}")
    public Boolean commentPost(@PathVariable("id") Long postId, @RequestBody Map<String, String> requestBody) {
        String content = requestBody.get("content");
        try {
            Post commentedPost = postService.getOne(postId);
            if (commentedPost == null) {
                log.warn("post is null");
                return false;
            }
            User loggedUser = userService.getByEmail(auditorAwareImpl.getCurrentAuditor().get()).orElse(null);
            if (loggedUser == null) {
                log.warn("loggedUser is null");
                return false;
            }
            if (content == null || content.trim().isEmpty()) {
                log.warn("Content is null or empty");
                return false;
            }
            Post newCommentPost = new Post(loggedUser, "comment", content, commentedPost);
            commentedPost.getComments().add(newCommentPost);
            postService.save(newCommentPost);
            postService.save(commentedPost);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/repost/{id}")
    public Boolean repostPost(@PathVariable("id") Long postId, @RequestBody Map<String, String> requestBody) {
        String content = requestBody.get("content");
        try {
            Post repostedPost = postService.getOne(postId);
            if (repostedPost == null) {
                log.warn("reposted post is null");
                return false;
            }
            User loggedUser = userService.getByEmail(auditorAwareImpl.getCurrentAuditor().get()).orElse(null);
            if (loggedUser == null) {
                log.warn("loggedUser is null");
                return false;
            }
            Post newPost = new Post(loggedUser, "post", content, repostedPost);

            repostedPost.addRepost(loggedUser, repostedPost);
            postService.save(newPost);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PutMapping("/remove-like-post/{id}")
    public Boolean removeLikePost(@PathVariable("id") Long postId) {
        try {
            Post post = postService.getOne(postId);

            if (post == null) {
                log.warn("Post is null");
                return false;
            }

            User loggedUser = userService.getByEmail(auditorAwareImpl.getCurrentAuditor().get()).get();
            if (loggedUser == null) {
                log.warn("LoggedUser is null");
                return false;
            }

            if (!post.getLikes().contains(loggedUser)) {
                log.warn("post is unliked already");
                return false;
            }

            if (post.removeLike(loggedUser)) {
                postService.save(post);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @PutMapping("/like-post/{id}")
    public Boolean likePost(@PathVariable("id") Long postId) {
        try {
            Post post = postService.getOne(postId);

            if (post == null) {
                log.warn("post is null");
                return false;
            }

            User loggedUser = userService.getByEmail(auditorAwareImpl.getCurrentAuditor().get()).get();
            if (loggedUser == null) {
                log.warn("loggedUser is null");
                return false;
            }

            if (post.getLikes().contains(loggedUser)) {
                log.warn("loggedUser is liked already");
                return false;
            }

            if (post.addLike(loggedUser)) {
                postService.save(post);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @GetMapping
    public List<PostDto> getAll() {
        List<Post> postList = postService.findAllPosts();

        List<PostDto> postDtoList = postList.stream().map(postDtoMapper::decorateDto).collect(Collectors.toList());
        return postDtoList;
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Sort sort =  Sort.by(new Sort.Order(Sort.Direction.DESC,"id"));
        Pageable pageable = PageRequest.of(page,size,sort);
        Page posts = postService.findAllPosts(pageable);
        List<Post> postList =  posts.toList();
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
    public boolean create(@RequestParam("content") String content, @RequestParam("files") List<MultipartFile> files) {
        User loggedUser = userService.getByEmail(auditorAwareImpl.getCurrentAuditor().get()).orElse(null);
        if (loggedUser == null) {
            return false;
        }

        Post newPost = new Post(loggedUser, "post", content, null);
        newPost = postService.save(newPost);

        if (!files.isEmpty()) {
            List<String> imgStringList;
            try {
                imgStringList = fileUpload.uploadPostFile(files, newPost.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Post finalNewPost = newPost;
            List<PostImage> imgUrlList = imgStringList.stream()
                    .map(img -> new PostImage(finalNewPost, img))
                    .collect(Collectors.toList());

            imgUrlList.forEach(postImage -> postImageService.save(postImage));

            newPost.setPostImages(imgUrlList);
            postService.save(newPost);
        }

        return true;
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
