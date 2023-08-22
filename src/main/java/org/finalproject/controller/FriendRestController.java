package org.finalproject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.*;
import org.finalproject.dto.friend.*;
import org.finalproject.entity.Friend;
import org.finalproject.entity.User;
import org.finalproject.service.DefaultFriendService;
import org.finalproject.service.jwt.UserService;
import org.finalproject.util.FriendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")
@CrossOrigin(origins = {"http://127.0.0.1:5173"})
public class FriendRestController {
    //private final GeneralService<Friend> friendService;
    @Autowired
    private DefaultFriendService defaultFriendService;
    private final FriendDtoMapper dtoMapper;
    private final FriendFullDtoMapper friendFullDtoMapper;
    private final UserDtoMapper userDtoMapper;
    private final FriendSuggestionsDtoMapper friendSuggestionsDtoMapper;
    private final UserService userService;

    @GetMapping
    public List<FriendFullDto> getAll() {
        return defaultFriendService.findAll().stream()
                .map(friendFullDtoMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Sort sort =  Sort.by(new Sort.Order(Sort.Direction.ASC,"id"));
        Pageable pageable = PageRequest.of(page,size,sort);
        List<FriendFullDto> friendDtoList = defaultFriendService.findAll(pageable)
                .toList()
                .stream()
                .map(friendFullDtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(friendDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id")  Long  userId) {
        Friend friend = defaultFriendService.getOne(userId );
        if (friend   == null) {
            return ResponseEntity.badRequest().body("Employer not found");
        }
        return ResponseEntity.ok().body(dtoMapper.convertToDto(friend));
    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<?> getFriends(@PathVariable("userId")  Long  userId) {
        List<FriendDto> usersFriends = defaultFriendService.friendsOfUser(userId)
                .stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(usersFriends);
    }

    @PostMapping("/search")
    public ResponseEntity<?> getFriendsByName(@RequestBody String parametersJson) throws JsonProcessingException {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode nameNodeAccountNumber = mapper.readTree(parametersJson);
            String friendName = nameNodeAccountNumber.get("friendName").asText();

            List<FriendDto> usersFriends = defaultFriendService.getFriendByName(user.get().getId(), friendName)
                    .stream()
                    .map(dtoMapper::convertToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(usersFriends);
        }
        return ResponseEntity.badRequest().body("Unauthorized user");
    }

    @GetMapping("/userFriends")
    public ResponseEntity<?> getUserFriends() {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            List<FriendDto> usersFriends = defaultFriendService.friendsOfUser(user.get().getId())
                    .stream()
                    .map(dtoMapper::convertToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(usersFriends);
        }
        return ResponseEntity.badRequest().body("Unauthorized user");
    }

    @GetMapping("/requests")
    public ResponseEntity<?> getRequests() {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            List<FriendFullDto> friendRequests = defaultFriendService.friendshipRequests(user.get().getId())
                    .stream()
                    .map(friendFullDtoMapper::convertToDto)
                    .toList();
            return ResponseEntity.ok().body(friendRequests);
        }
        return ResponseEntity.badRequest().body("Unauthorized user");

    }

    @GetMapping("/suggestions")
    public ResponseEntity<?> getSuggestions() {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            List<FriendSuggestionsDto> friendSuggestions = defaultFriendService.suggestedUsersForFriendship(user.get().getId())
                    .stream()
                    .map(friendSuggestionsDtoMapper::convertToDto)
                    .map(el -> {
                        el.setMutualFriends(defaultFriendService.getMutualFriends(user.get().getId(), el.getFriend().getId())
                                .stream()
                                .map(userDtoMapper::convertToDto)
                                .toList());
                        return el;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(friendSuggestions);
        }
        return ResponseEntity.badRequest().body("Unauthorized user");
    }

    @GetMapping("/birthdays")
    public ResponseEntity<?> getBirthdays() {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            List<List<FriendFullDto>> birthdays = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                int finalI = (i + (new Date()).getMonth()) % 12;
                birthdays.add(defaultFriendService.friendsOfUser(user.get().getId())
                        .stream()
                        .filter(fr -> fr.getFriend().getBirthDate() != null && fr.getFriend().getBirthDate().getMonth() == finalI
                                && fr.getStatus() == FriendStatus.accepted)
                        .map(friendFullDtoMapper::convertToDto).toList());
            }
            return ResponseEntity.ok().body(birthdays);
        }
        return ResponseEntity.badRequest().body("Unauthorized user");
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody String parametersJson ) throws JsonProcessingException {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userService.getByEmail(auth);
        if (user.isPresent()) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode nameNodeAccountNumber = mapper.readTree(parametersJson);
            Long friendId = Long.parseLong(nameNodeAccountNumber.get("friendId").asText());
            Friend newFriend = defaultFriendService.saveNewById(user.get().getId(), friendId);
            return ResponseEntity.ok().body(friendFullDtoMapper.convertToDto(newFriend));
        }
        return ResponseEntity.badRequest().body("Unauthorized user");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long userId) {
        try {
            defaultFriendService.deleteById(userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEmployee(@RequestBody FriendRequestDto friend) {
        try {
            defaultFriendService.delete(dtoMapper.convertToEntity(friend));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> changeStatus(@RequestBody FriendChangeStatusRequestDto requestForChange) throws JsonProcessingException {
        Friend result;
        try {
            result = defaultFriendService.changeStatus(requestForChange.getId(), requestForChange.getStatus());
            return (result != null
                    ? ResponseEntity.ok().body(friendFullDtoMapper.convertToDto(result))
                    : ResponseEntity.badRequest().body("Such friendship have not found."));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
