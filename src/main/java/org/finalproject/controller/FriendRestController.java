package org.finalproject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.*;
import org.finalproject.entity.Friend;
import org.finalproject.entity.User;
import org.finalproject.service.DefaultFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.finalproject.repository.FriendJpaRepository;
import org.finalproject.service.DefaultFriendService;
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
@RequestMapping("/friends")
public class FriendRestController {
    //private final GeneralService<Friend> friendService;
    @Autowired
    private DefaultFriendService defaultFriendService;
    private final FriendDtoMapper dtoMapper;
    private final FriendFullDtoMapper friendFullDtoMapper;
    private final UserDtoMapper userDtoMapper;
    private final FriendSuggestionsDtoMapper friendSuggestionsDtoMapper;

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
        Page friends = defaultFriendService.findAll(pageable);
        List<Friend> friendList =  friends.toList();
        List<FriendFullDto> friendDtoList = friendList.stream().map(dtoMapper::convertToFullDto).collect(Collectors.toList());
        return ResponseEntity.ok(friendDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getById(@PathVariable("id")  Long  userId) {
        Friend friend = defaultFriendService.getOne(userId );
        if (friend   == null) {
            return ResponseEntity.badRequest().body("Employer not found");
        }
        return ResponseEntity.ok().body(dtoMapper.convertToDto(friend));
    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<?>  getFriends(@PathVariable("userId")  Long  userId) {
        List<Friend> friends = defaultFriendService.friendsOfUser(userId);
        List<FriendDto> usersFriends = friends.stream().map(dtoMapper::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersFriends);
    }

    @GetMapping("/{userId}/requests")
    public ResponseEntity<?>  getRequests(@PathVariable("userId")  Long  userId) {
        List<Friend> friends = defaultFriendService.friendshipRequests(userId);
        List<FriendFullDto> friendRequests = friends.stream().map(friendFullDtoMapper::convertToDto).toList();
        return ResponseEntity.ok().body(friendRequests);
    }

    @GetMapping("/{userId}/suggestions")
    public ResponseEntity<?>  getSuggestions(@PathVariable("userId")  Long  userId) {
        List<User> users = defaultFriendService.suggestedUsersForFriendship(userId);
        List<FriendSuggestionsDto> friendSuggestions = users.stream()
                .map(friendSuggestionsDtoMapper::convertToDto)
                .map(el -> { el.setMutualFriends(defaultFriendService.getMutualFriends(userId, el.getFriend().getId()));
                    return el; })
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(friendSuggestions);
    }

    /*@PostMapping
    public ResponseEntity<?> create(@RequestBody FriendRequestDto friend ) {
        Friend newFriend = defaultFriendService.save(dtoMapper.convertToEntity(friend) );
        return ResponseEntity.ok().body(newFriend);
    }*/

    @PostMapping
    public ResponseEntity<?> create(@RequestBody String parametersJson ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode nameNodeAccountNumber = mapper.readTree(parametersJson);
        Long userId = Long.parseLong(nameNodeAccountNumber.get("userId").asText());
        Long friendId = Long.parseLong(nameNodeAccountNumber.get("friendId").asText());
        Friend newFriend = defaultFriendService.saveNewById(userId, friendId);
        return ResponseEntity.ok().body(friendFullDtoMapper.convertToDto(newFriend));
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
    public ResponseEntity<?> update(@RequestBody FriendRequestDto friendRequestDto) throws JsonProcessingException {
        Friend result;
        try {
            result = defaultFriendService.update(dtoMapper.convertToEntity(friendRequestDto));
            return (result != null
                    ? ResponseEntity.ok().body(friendFullDtoMapper.convertToDto(result))
                    : ResponseEntity.badRequest().body("Such friendship have not found."));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
