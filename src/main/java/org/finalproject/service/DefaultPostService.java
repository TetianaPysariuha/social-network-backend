package org.finalproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.finalproject.config.AuditorAwareImpl;
import org.finalproject.dto.post.PostDto;
import org.finalproject.entity.Notification;
import org.finalproject.entity.Post;
import org.finalproject.entity.PostImage;
import org.finalproject.entity.User;
import org.finalproject.repository.PostJpaRepository;
import org.finalproject.util.NotificationStatus;
import org.finalproject.util.NotificationType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultPostService extends GeneralService<Post> {
    private final PostJpaRepository postRepository;
    private final DefaultUserService userService;
    private final AuditorAwareImpl auditorAwareImpl;
    private final FileUpload fileUpload;
    private final DefaultPostImageService postImageService;
    private final GeneralService<Notification> notificationService;
    private final RabbitTemplate rabbitTemplate;



    public Boolean commentPost(Long postId, String content) {
        try {
            Post commentedPost = postRepository.findEntityById(postId);
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
            postRepository.save(newCommentPost);
            postRepository.save(commentedPost);
            if (!commentedPost.getUser().equals(loggedUser)) {
                Notification notification = notificationService.save(
                        new Notification(NotificationType.newComment,
                        NotificationStatus.pending, loggedUser,
                "commented your post.",
                        commentedPost.getId(),
                        List.of(commentedPost.getUser())));
                commentedPost.getUser().getNotifications().add(notification);
                userService.save(commentedPost.getUser());
                rabbitTemplate.convertAndSend("notification-exchange", "user." + commentedPost.getUser().getId(), notification);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean repostPost(Long postId, String content) {
        try {
            Post repostedPost = postRepository.findEntityById(postId);
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
            repostedPost.getReposts().add(newPost);
            postRepository.save(repostedPost);
            postRepository.save(newPost);
            if (!repostedPost.getUser().equals(loggedUser)) {
                Notification notification = notificationService.save(new Notification(NotificationType.newRepost, NotificationStatus.pending, loggedUser, "shared your post.", repostedPost.getId(), List.of(repostedPost.getUser())));
                repostedPost.getUser().getNotifications().add(notification);
                userService.save(repostedPost.getUser());
                rabbitTemplate.convertAndSend("notification-exchange", "user." + repostedPost.getUser().getId(), notification);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean removeLikePost(Long postId) {
        try {
            Post post = postRepository.findEntityById(postId);

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
                postRepository.save(post);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean likePost(Long postId) {
        try {
            Post post = postRepository.findEntityById(postId);

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
                postRepository.save(post);
                if (!post.getUser().equals(loggedUser)) {
                    Notification notification = notificationService.save(new Notification(NotificationType.newLike, NotificationStatus.pending, loggedUser, "likes your post.", post.getId(), List.of(post.getUser())));
                    post.getUser().getNotifications().add(notification);
                    userService.save(post.getUser());
                    rabbitTemplate.convertAndSend("notification-exchange", "user." + post.getUser().getId(), notification);
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Post> findAll( Integer page, Integer size) {
        Sort sort =  Sort.by(new Sort.Order(Sort.Direction.DESC,"id"));
        Pageable pageable = PageRequest.of(page,size,sort);
        Page posts = postRepository.findAllPosts(pageable);
        List<Post> postList =  posts.toList();
        return postList;
    }

    public Post create(String content, List<MultipartFile> files) {
        User loggedUser = userService.getByEmail(auditorAwareImpl.getCurrentAuditor().get()).orElse(null);
        if (loggedUser == null) {
            return null;
        }

        Post newPost = new Post(loggedUser, "post", content, null);
        newPost = postRepository.save(newPost);

        if (files != null) {
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
            newPost = postRepository.save(newPost);
        }

        return newPost;
    }



    public List<Post> findAllPosts() {
        return postRepository.findAllPosts();
    }

    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAllPosts(pageable);
    }

}
