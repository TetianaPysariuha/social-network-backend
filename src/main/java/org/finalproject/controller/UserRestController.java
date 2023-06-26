package org.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.UserDto;

import org.finalproject.dto.UserDtoMapper;
import org.finalproject.dto.UserRequestDto;


import org.finalproject.entities.User;
import org.finalproject.service.DefaultUserService;
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
@RequestMapping("/users")
public class UserRestController {
    private final GeneralService<User> userService;

    private final UserDtoMapper dtoMapper;




    @GetMapping
    public List<UserDto> getAll() {
        // return userService.findAll().stream().map(dtoMapper::convertToDto).collect(Collectors.toList());
    List<User> userList =    userService.findAll();
       List<UserDto> userDtoList = userList.stream().map(dtoMapper::convertToDto).collect(Collectors.toList());
        // return userList;
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
    public void create(@RequestBody UserRequestDto employee ) {
        userService.save(dtoMapper.convertToEntity(employee) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id")Long userId) {
        try {
            userService.deleteById(userId);
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
