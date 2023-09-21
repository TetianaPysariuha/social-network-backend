package org.finalproject.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.finalproject.dto.*;

import org.finalproject.dto.chat.ChatDto;
import org.finalproject.dto.chat.ChatDtoMapper;
import org.finalproject.dto.friend.FriendDto;
import org.finalproject.dto.friend.FriendDtoMapper;
import org.finalproject.dto.post.PostDto;
import org.finalproject.dto.post.PostDtoMapper;
import org.finalproject.dto.post.PostRequestDto;

import org.finalproject.entity.*;
import org.finalproject.filter.JwtFilter;
import org.finalproject.jwt.Email;
import org.finalproject.service.DefaultFriendService;
import org.finalproject.service.DefaultUserService;
import org.finalproject.service.FileUpload;

import org.finalproject.service.GeneralService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final GeneralService<User> userService;

    private final GeneralService<Post> postService;

    private final DefaultFriendService defaultService;

    private final DefaultUserService defaultUserService;

    private final GeneralService<UserImage> imageService;

    private final UserDtoMapper dtoMapper;

    private final NotificationDtoMapper notificationDtoMapper;

    private final PostDtoMapper postMapper;

    private final ChatDtoMapper chatMapper;

    private final JwtFilter jwt;

    private final FriendDtoMapper friendMapper;

    private final FileUpload fileUpload;

    private  final UserImageDtoMapper imageMapper;


    @GetMapping
    public List<UserDto> getAll() {

        return userService.findAll()
                .stream()
                .map(dtoMapper::convertToDto).collect(Collectors.toList());

    }

    @GetMapping("/{page}/{size}")

    public ResponseEntity<List<UserDto>> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = PageRequest.of(page, size, sort);
        List<UserDto> userDtoList = userService.findAll(pageable).toList()
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDtoList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long userId) {
        User user = defaultUserService.getOne(userId);
        return ResponseEntity.ok().body(dtoMapper.convertToDto(user));
    }

    @GetMapping("/email")
    public ResponseEntity<UserDto> getByEmail(@RequestBody Email email) {
        Optional<User> userOptional = defaultUserService.getByEmail(email.getEmail());

        return ResponseEntity.ok().body(dtoMapper.convertToDto(userOptional.get()));
    }

    @GetMapping("/part")
    public ResponseEntity<List<UserDto>> getByPartOfName(@RequestParam String part) {
        List<User> userList = defaultUserService.getUserByPartOfName(part);

        return ResponseEntity.ok().body(userList.stream().map(dtoMapper::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile() {

        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> profile = defaultUserService.getByEmail(auth);

        if (profile.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().body(dtoMapper.convertToDto(profile.get()));
    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<FriendDto>> getFriends(@PathVariable("userId") Long userId) {

        List<FriendDto> friendDtoList = defaultService.friendsOfUser(userId)
                .stream()
                .map(friendMapper::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok().body(friendDtoList);
    }

    @GetMapping("/{id}/chats")
    public ResponseEntity<List<ChatDto>> getChats(@PathVariable("id") Long userId) {
        User user = userService.getOne(userId);

        if (user == null) {
            throw new EntityNotFoundException();
        }
        List<ChatDto> userChatsDto = user.getChats()
                .stream()
                .map(chatMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(userChatsDto);
    }

    @GetMapping("/chats")
    public ResponseEntity<List<ChatDto>> getAuthorizedUserChats() {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> profile = defaultUserService.getByFullName(auth);

        if (profile.isEmpty()) {
            throw new EntityNotFoundException();
        }
        List<ChatDto> userChatsDto = profile.get().getChats()
                .stream()
                .map(chatMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(userChatsDto);
    }


    @GetMapping("/images")
    public ResponseEntity<?> getAuthorizedUserImages() {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> profile = defaultUserService.getByFullName(auth);

        if (profile.isEmpty()) {
            throw new EntityNotFoundException();
        }
        List<UserImageDto> userImageDto = profile.get().getUserImages()
                .stream()
                .map(imageMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(userImageDto);
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<?> getUserImages(@PathVariable Long id) {
      User user = userService.getOne(id);
        if (user == null ) {
            throw new EntityNotFoundException();
        }
        List<UserImageDto> userImageDto = user.getUserImages()
                .stream()
                .map(imageMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(userImageDto);
    }

    @GetMapping("/{id}/posts")

    public ResponseEntity<?> getPosts(@PathVariable("id") Long userId) {
        User user = userService.getOne(userId );

        if (user   == null) {
           throw new EntityNotFoundException();
        }

        List<Post> userPosts = user.getPosts();
        List<PostDto> userPostsDto = userPosts.stream().map(postMapper::decorateDto).collect(Collectors.toList());
        return ResponseEntity.ok().body(userPostsDto );
    }

    @PostMapping
    public void create(@RequestBody @Valid UserRequestDto user) {
        userService.save(dtoMapper.convertToEntity(user));
    }

    @PostMapping("/image")
    public void uploadImage( @RequestParam("multipartFiles") List<MultipartFile> multipartFiles) {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> profile = defaultUserService.getByEmail(auth);
        if (profile.isEmpty()) {
            throw new EntityNotFoundException();
        }

        List<String> imgStringList;
        try {
            imgStringList  = fileUpload.uploadUseListOfrFiles(multipartFiles, profile.get().getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = userService.getOne(profile.get().getId());
        List<UserImage> userImages = user.getUserImages();
        List<UserImage> userImgList = new ArrayList<>();
        imgStringList.forEach(el-> {
            UserImage img = new UserImage();
            img.setImageUrl(el);
            img.setUser(user);
            img.setUserId(user.getId());
            imageService.save(img);
            userImgList.add(img);
        });

        userImages.addAll(userImgList);
        user.setUserImages(userImages);
        userService.save(user);


    }

    @PostMapping("/avatar")
    public void uploadAvatar( @RequestBody MultipartFile multipartFile) {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> profile = defaultUserService.getByEmail(auth);
        if (profile.isEmpty()) {
            throw new EntityNotFoundException();
        }
        String imgUrl = null;
        try {
            imgUrl = fileUpload.uploadUserFile(multipartFile, profile.get().getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = userService.getOne(profile.get().getId());
        user.setProfilePicture(imgUrl);
        userService.save(user);
    }

    @PostMapping("/header")
    public void uploadHeader( @RequestBody MultipartFile multipartFile) {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> profile = defaultUserService.getByEmail(auth);
        if (profile.isEmpty()) {
            throw new EntityNotFoundException();
        }
        String imgUrl = null;
        try {
            imgUrl = fileUpload.uploadUserFile(multipartFile, profile.get().getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = userService.getOne(profile.get().getId());
        user.setProfileBackgroundPicture(imgUrl);
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {

        defaultUserService.deleteUserById(id);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody UserRequestDto user) {

        defaultUserService.deleteUserById(user.getId());
        return ResponseEntity.ok().build();

    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody  UserRequestDto user) {

        User userEntity = dtoMapper.convertToEntity(user);
        defaultUserService.update(userEntity);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}/likes")
    public ResponseEntity<?> addLikes(@RequestParam Long id, @RequestBody PostRequestDto post) {

        Post postEntity = postService.getOne(post.getId());
        defaultUserService.addLikes(id, postEntity);
        return ResponseEntity.ok().build();
    }


}