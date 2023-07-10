package org.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.UserDto;

import org.finalproject.dto.UserDtoMapper;
import org.finalproject.dto.UserRequestDto;
import org.finalproject.service.FileUpload;

import org.finalproject.entity.User;
import org.finalproject.entity.UserImage;
import org.finalproject.service.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {
    private final GeneralService<User> userService;

    private final UserDtoMapper dtoMapper;

    private final FileUpload fileUpload;


    @GetMapping
    public List<UserDto> getAll() {

       List<User> userList =    userService.findAll();
       List<UserDto> userDtoList = userList.stream().map(dtoMapper::convertToDto).collect(Collectors.toList());

        return userDtoList;
    }

    @GetMapping("/{page}/{size}")

    public ResponseEntity<?> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Sort sort =  Sort.by(new Sort.Order(Sort.Direction.ASC,"id"));
        Pageable pageable = PageRequest.of(page,size,sort);
     Page users = userService.findAll(pageable);
     List<User> userList =  users.toList();
        List<UserDto> userDtoList = userList.stream().map(dtoMapper::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getById(@PathVariable("id")  Long  userId) {
        User user = userService.getOne(userId );

        if (user   == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok().body(dtoMapper.convertToDto(user) );
    }

    @GetMapping("/{id}/friends")
    public ResponseEntity<?>  getFriends(@PathVariable("id")  Long  userId) {
        User user = userService.getOne(userId );

        if (user   == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok().body(user.getFriends() );
    }

    @GetMapping("/{id}/chats")
    public ResponseEntity<?>  getChats(@PathVariable("id")  Long  userId) {
        User user = userService.getOne(userId );

        if (user   == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok().body(user.getChats() );
    }

    @GetMapping("/{id}/posts")

    public ResponseEntity<?>  getPosts(@PathVariable("id")  Long  userId) {
        User user = userService.getOne(userId );

        if (user   == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok().body(user.getPosts() );
    }

    @PostMapping
    public void create(@RequestBody UserRequestDto user ) {
        userService.save(dtoMapper.convertToEntity(user) );
    }

    @PostMapping("/{id}/image")
    public void uploadImage(@PathVariable Long id,@RequestBody MultipartFile multipartFile ) {
        String imgUrl = null;
        try {
            imgUrl = fileUpload.uploadUserFile(multipartFile,id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = userService.getOne(id);
     List<UserImage> userImages = user.getUserImages();
     UserImage newImage = new UserImage();
     newImage.setImageUrl(imgUrl);
     userImages.add(newImage);
     user.setUserImages(userImages);
        userService.save(user );
    }

    @PostMapping("/{id}/avatar")
    public void uploadAvatar(@PathVariable Long id,@RequestBody MultipartFile multipartFile ) {
        String imgUrl = null;
        try {
            imgUrl = fileUpload.uploadUserFile(multipartFile,id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = userService.getOne(id);
       user.setProfilePicture(imgUrl);
        userService.save(user );
    }

    @PostMapping("/{id}/header")
    public void uploadHeader(@PathVariable Long id,@RequestBody MultipartFile multipartFile ) {
        String imgUrl = null;
        try {
            imgUrl = fileUpload.uploadUserFile(multipartFile,id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = userService.getOne(id);
        user.setProfileBackgroundPicture(imgUrl);
        userService.save(user );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id")Long userId) {
        try {

            userService.delete(userService.findEntityById(userId));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody UserRequestDto user) {
        try {
            userService.delete(dtoMapper.convertToEntity(user));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserRequestDto user) {
        try {
            userService.save(dtoMapper.convertToEntity(user));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
