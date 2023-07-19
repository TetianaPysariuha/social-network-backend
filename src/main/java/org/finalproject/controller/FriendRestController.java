package org.finalproject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.*;
import org.finalproject.entity.Friend;
import org.finalproject.entity.User;
import org.finalproject.service.DefaultFriendService;
import org.finalproject.service.GeneralService;
import org.finalproject.utilites.FriendStatus;
import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins = {"http://127.0.0.1:5173/"})
public class FriendRestController {
    private final GeneralService<Friend> friendService;

    @Autowired
    private DefaultFriendService defaultFriendService;

    private final FriendDtoMapper dtoMapper;
    private final FriendFullDtoMapper friendFullDtoMapper;
    private final UserDtoMapper userDtoMapper;



    @GetMapping
    public List<FriendFullDto> getAll() {
        return friendService.findAll().stream().map(friendFullDtoMapper::convertToDto).collect(Collectors.toList());

    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Sort sort =  Sort.by(new Sort.Order(Sort.Direction.ASC,"id"));
        Pageable pageable = PageRequest.of(page,size,sort);
        Page friends = friendService.findAll(pageable);
        List<Friend> friendList =  friends.toList();
        List<FriendDto> friendDtoList = friendList.stream().map(dtoMapper::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(friendDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getById(@PathVariable("id")  Long  userId) {
        Friend friend = friendService.getOne(userId );

        if (friend   == null) {
            return ResponseEntity.badRequest().body("Employer not found");
        }
        return ResponseEntity.ok().body(dtoMapper.convertToDto(friend) );
    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<?>  getFriends(@PathVariable("userId")  Long  userId) {


        //List<Friend> friends = friendService.findAll().stream().filter(el -> el.getUser().getId().equals(userId))
                //.collect(Collectors.toList());
        List<Friend> friends = defaultFriendService.friendsOfUser(userId);
        List<FriendDto> usersFriends = friends.stream().map(dtoMapper::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersFriends);
    }
    @GetMapping("/{userId}/suggestions")
    public ResponseEntity<?>  getSuggestions(@PathVariable("userId")  Long  userId) {
        List<User> users = defaultFriendService.suggestedUsersForFriendship(userId);
        List<UserDto> friendSuggestions = users.stream().map(userDtoMapper::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok().body(friendSuggestions);
    }

//    @PostMapping
//    public ResponseEntity<?> create(@RequestBody FriendRequestDto friend ) {
//        Friend newFriend = defaultFriendService.save(dtoMapper.convertToEntity(friend) );
//        return ResponseEntity.ok().body(newFriend);
//    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody String parametersJson ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode nameNodeAccountNumber = mapper.readTree(parametersJson);
        Long userId = nameNodeAccountNumber.get("userId").asLong();
        Long friendId = nameNodeAccountNumber.get("friendId").asLong();
        Friend newFriend = defaultFriendService.saveNewById(userId, friendId);
        return ResponseEntity.ok().body(newFriend);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id")Long userId) {
        try {
            friendService.deleteById(userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEmployee(@RequestBody FriendRequestDto friend) {
        try {
            friendService.delete(dtoMapper.convertToEntity(friend));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

/*    @PutMapping
    public ResponseEntity<?> update(@RequestBody String parametersJson ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode friendUpdate = mapper.readTree(parametersJson);
        Long userId = friendUpdate.get("userId").asLong();
        Long friendId = friendUpdate.get("friendId").asLong();
        Long id = friendUpdate.get("id").asLong();
        String status = friendUpdate.get("status").asText();
        System.out.println(status);
        FriendStatus friendStatus = FriendStatus.forValue(status);
        System.out.println(friendStatus);

        try {
            defaultFriendService.update(id,friendStatus,userId, friendId );
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

    @PutMapping
    public ResponseEntity<?> update(@RequestBody FriendRequestDto friendRequestDto) throws JsonProcessingException {

        try {
            defaultFriendService.save(dtoMapper.convertToEntity(friendRequestDto));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
