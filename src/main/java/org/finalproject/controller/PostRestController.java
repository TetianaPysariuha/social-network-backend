package org.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.codehaus.jackson.map.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.finalproject.config.AuditorAwareImpl;
import org.finalproject.dto.PostDto;
import org.finalproject.dto.PostDtoMapper;
import org.finalproject.dto.PostRequestDto;
import org.finalproject.entity.Post;
import org.finalproject.entity.User;
import org.finalproject.service.DefaultPostService;
import org.finalproject.service.DefaultUserService;
import org.finalproject.service.FileUpload;
import org.finalproject.service.RabbitMQProducerServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


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
    private final RabbitMQProducerServiceImpl rabbitMQProducerServiceImpl;
    private final FileUpload fileUpload;


    @PostMapping("/comment/{id}")
    public Boolean commentPost(@PathVariable("id") Long postId, @RequestBody Map<String, String> requestBody) {
        String content = requestBody.get("content");
        try {
            Post commentedPost = postService.getOne(postId);
            System.out.println(content);
            if (commentedPost == null) {
                log.warn("post is null");
                return false;
            }
            System.out.println(auditorAwareImpl.getCurrentAuditor().get());
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
            //ObjectMapper objectMapper = new ObjectMapper();
            //String postJson = objectMapper.writeValueAsString(newCommentPost);
            //RabbitMQProducerServiceImpl.sendMessage(postJson, "commentRoutingKey");
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
            //  loggedUser.getReposts().add(newPost);
            postService.save(newPost);
            userService.save(loggedUser);
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

            System.out.println(loggedUser.getLikedPosts());
            if (post.removeLike(loggedUser)) {
                System.out.println(loggedUser.getLikedPosts());
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

            System.out.println(loggedUser.getLikedPosts());
            if (post.addLike(loggedUser)) {
                System.out.println(loggedUser.getLikedPosts());
                postService.save(post);

                ObjectMapper objectMapper = new ObjectMapper();

                //String postJson = "{\"loggedUserId\":" + loggedUser.getId() + ", \"postId\":" + postId + "}";
                //RabbitMQProducerServiceImpl.sendMessage(postJson, "commentRoutingKey");
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
        List<Post> postList = postService.findAll();
        List<PostDto> postDtoList = postList.stream().map(dtoMapper::convertToDto).collect(Collectors.toList());
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
        //fileUpload.uploadPostFile()
        //Post newPost = new Post(fileUpload.uploadPostFile());
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
