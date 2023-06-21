package org.finalproject.controller;





import lombok.RequiredArgsConstructor;
import org.finalproject.dto.FriendDto;

import org.finalproject.dto.FriendDtoMapper;
import org.finalproject.dto.FriendRequestDto;

import org.finalproject.dto.UserDto;
import org.finalproject.entities.Friend;
import org.finalproject.entities.User;
import org.finalproject.service.DefaultFriendService;
import org.finalproject.service.DefaultUserService;
import org.finalproject.service.GeneralService;
import org.finalproject.service.ServiceInterface;
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
@RequestMapping("/friends")
public class FriendRestController {
    private final DefaultFriendService friendService;

    private final DefaultUserService userService;

    private final FriendDtoMapper dtoMapper;




    @GetMapping
    public List<FriendDto> getAll() {
        return friendService.findAll().stream().map(dtoMapper::convertToDto).collect(Collectors.toList());

    }
    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Sort sort =  Sort.by(new Sort.Order(Sort.Direction.ASC,"id"));
        Pageable pageable = PageRequest.of(page,size,sort);
        Page friends =friendService.findAll(pageable);
        List <Friend> friendList =  friends.toList();
        List<FriendDto> friendDtoList =friendList.stream().map(dtoMapper::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(friendDtoList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>  getById(@PathVariable("id")  Long  userId) {
        Friend friend = friendService.getOne(userId );

        if (friend   == null){
            return ResponseEntity.badRequest().body("Employer not found");
        }
        return ResponseEntity.ok().body(dtoMapper.convertToDto(friend) );
    }
    @GetMapping("/{userId}/friends")
    public ResponseEntity<?>  getFriends(@PathVariable("userId")  Long  userId) {


        List <Friend> friends = friendService.findAll().stream().filter(el -> el.getUser().getId().equals(userId)).collect(Collectors.toList());

        List <User> usersFriends = friends.stream().map(el -> el.getFriend()).collect(Collectors.toList());

        return ResponseEntity.ok().body(usersFriends);

    }

    @PostMapping
    public void create(@RequestBody FriendRequestDto friend ){
        friendService.save(dtoMapper .convertToEntity(friend) );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id")Long userId) {
        try {
            friendService.deleteById(userId);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<?> deleteEmployee(@RequestBody FriendRequestDto friend) {
        try {
            friendService.delete(dtoMapper.convertToEntity(friend));
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity <?> update(@RequestBody FriendRequestDto friend) {
        try {
            friendService.save(dtoMapper.convertToEntity(friend));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
