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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
        Page friends = defaultFriendService.findAll(pageable);
        List<Friend> friendList =  friends.toList();
        List<FriendFullDto> friendDtoList = friendList.stream().map(friendFullDtoMapper::convertToDto).collect(Collectors.toList());
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

    @PostMapping("/search")
    public ResponseEntity<?> getFriendsByName(@RequestBody String parametersJson) throws JsonProcessingException {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        try {
            User user = userService.getByEmail(auth).get();
            ObjectMapper mapper = new ObjectMapper();

            System.out.println(parametersJson);

            JsonNode nameNodeAccountNumber = mapper.readTree(parametersJson);
            String friendName = nameNodeAccountNumber.get("friendName").asText();

            System.out.println(friendName);
            System.out.println(user.getId());


            List<Friend> friends = defaultFriendService.getFriendByName(user.getId(), friendName);
            List<FriendDto> usersFriends = friends.stream().map(dtoMapper::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok().body(usersFriends);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/userFriends")
    public ResponseEntity<?>  getUserFriends() {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.getByEmail(auth).get();
        List<Friend> friends = defaultFriendService.friendsOfUser(user.getId());
        List<FriendDto> usersFriends = friends.stream().map(dtoMapper::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersFriends);
    }

    @GetMapping("/requests")
    public ResponseEntity<?>  getRequests() {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        try {
            User user = userService.getByEmail(auth).get();
            List<Friend> friends = defaultFriendService.friendshipRequests(user.getId());
            List<FriendFullDto> friendRequests = friends.stream().map(friendFullDtoMapper::convertToDto).toList();
            return ResponseEntity.ok().body(friendRequests);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/suggestions")
    public ResponseEntity<?>  getSuggestions() {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        try {
            User user = userService.getByEmail(auth).get();
            List<User> users = defaultFriendService.suggestedUsersForFriendship(user.getId());
            List<FriendSuggestionsDto> friendSuggestions = users.stream()
                    .map(friendSuggestionsDtoMapper::convertToDto)
                    .map(el -> {
                        el.setMutualFriends(defaultFriendService.getMutualFriends(user.getId(), el.getFriend().getId())
                                .stream()
                                .map(fr -> userDtoMapper.convertToDto(fr))
                                .toList());
                        return el;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(friendSuggestions);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /*@PostMapping
    public ResponseEntity<?> create(@RequestBody FriendRequestDto friend ) {
        Friend newFriend = defaultFriendService.save(dtoMapper.convertToEntity(friend) );
        return ResponseEntity.ok().body(newFriend);
    }*/

    @PostMapping
    public ResponseEntity<?> create(@RequestBody String parametersJson ) throws JsonProcessingException {
        String auth  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        try {
            User user = userService.getByEmail(auth).get();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode nameNodeAccountNumber = mapper.readTree(parametersJson);
            Long friendId = Long.parseLong(nameNodeAccountNumber.get("friendId").asText());
            Friend newFriend = defaultFriendService.saveNewById(user.getId(), friendId);
            return ResponseEntity.ok().body(friendFullDtoMapper.convertToDto(newFriend));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
