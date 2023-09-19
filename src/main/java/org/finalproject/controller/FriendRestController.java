package org.finalproject.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.friend.*;
import org.finalproject.entity.Friend;
import org.finalproject.service.DefaultFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendRestController {
    @Autowired
    private DefaultFriendService defaultFriendService;
    private final FriendDtoMapper dtoMapper;
    private final FriendFullDtoMapper friendFullDtoMapper;
    private final FriendSuggestionsDtoMapper friendSuggestionsDtoMapper;

    @GetMapping
    public List<FriendFullDto> getAll() {
        return defaultFriendService.findAll().stream()
                .map(friendFullDtoMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<List<FriendFullDto>> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC,"id"));
        Pageable pageable = PageRequest.of(page,size,sort);
        List<FriendFullDto> friendDtoList = defaultFriendService.findAll(pageable)
                .toList()
                .stream()
                .map(friendFullDtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(friendDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FriendDto> getById(@PathVariable("id") Long friendId) {
        Friend friend = defaultFriendService.getOne(friendId);
        if (friend == null) {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().body(dtoMapper.convertToDto(friend));
    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<FriendDto>> getFriends(@PathVariable("userId") Long userId) {
        List<FriendDto> usersFriends = defaultFriendService.friendsOfUser(userId)
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(usersFriends);
    }

    @PostMapping("/search")
    public ResponseEntity<List<FriendDto>> getFriendsByName(@RequestBody Map<String, String> requestMap) {
        System.out.println(requestMap);
        String friendName = requestMap.get("friendName");
        List<FriendDto> usersFriends = defaultFriendService.getFriendByName(friendName)
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(usersFriends);
    }

    @GetMapping("/userFriends")
    public ResponseEntity<List<FriendDto>> getUserFriends() {
        List<FriendDto> usersFriends = defaultFriendService.friendsOfUser()
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(usersFriends);
    }

    @GetMapping("/userFriends/{page}/{size}")
    public ResponseEntity<List<FriendDto>> getUserFriendsPageable(@PathVariable Integer page, @PathVariable Integer size) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC,"id"));
        Pageable pageable = PageRequest.of(page,size,sort);
        List<FriendDto> usersFriends = defaultFriendService.friendsOfUser(pageable)
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(usersFriends);
    }

    @GetMapping("/requests")
    public ResponseEntity<List<FriendFullDto>> getRequests() {
        List<FriendFullDto> friendRequests = defaultFriendService.friendshipRequests()
                .stream()
                .map(friendFullDtoMapper::convertToDto)
                .toList();
        return ResponseEntity.ok().body(friendRequests);
    }

    @GetMapping("/suggestions/{page}/{size}")
    public ResponseEntity<List<FriendSuggestionsDto>> getSuggestionsPageable(@PathVariable Integer page, @PathVariable Integer size) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC,"id"));
        Pageable pageable = PageRequest.of(page,size,sort);
        List<FriendSuggestionsDto> friendSuggestions = defaultFriendService.suggestedUsersForFriendship(pageable)
                .stream()
                .map(friendSuggestionsDtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(friendSuggestions);
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<FriendSuggestionsDto>> getSuggestions() {
        List<FriendSuggestionsDto> friendSuggestions = defaultFriendService.suggestedUsersForFriendship()
                .stream()
                .map(friendSuggestionsDtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(friendSuggestions);
    }

    @GetMapping("/birthdays")
    public ResponseEntity<List<List<FriendDto>>> getBirthdays() {
        return ResponseEntity.ok().body(defaultFriendService.getBirthdays());
    }

    @PostMapping
    public ResponseEntity<FriendFullDto> create(@Valid @RequestBody FriendRequestCreateDto friendRequestCreateDto) {
        Friend newFriend = defaultFriendService.saveNewById(friendRequestCreateDto);
        return ResponseEntity.ok().body(friendFullDtoMapper.convertToDto(newFriend));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long userId) {
        defaultFriendService.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEmployee(@Valid @RequestBody FriendRequestDto friend) {
        defaultFriendService.delete(dtoMapper.convertToEntity(friend));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<FriendFullDto> changeStatus(@Valid @RequestBody FriendChangeStatusRequestDto requestForChange) {
        Friend result = defaultFriendService.changeStatus(requestForChange.getId(), requestForChange.getStatus());
        return ResponseEntity.ok().body(friendFullDtoMapper.convertToDto(result));
    }
}
