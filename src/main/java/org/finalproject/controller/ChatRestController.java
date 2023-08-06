package org.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.*;
import org.finalproject.entity.Chat;
import org.finalproject.entity.User;
import org.finalproject.service.DefaultChatService;
import org.finalproject.service.GeneralService;
import org.finalproject.service.jwt.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatRestController {

    private final GeneralService<Chat> chatService;
    private final DefaultChatService defaultChatService;

    private final UserService userService;
    private final GeneralService<User> generalService;
    private final ChatDtoMapper chatDtoMapper;
    private final UserDtoMapper userDtoMapper;

    @GetMapping
    public List<ChatDto> getAll() {

        List<Chat> chatList = chatService.findAll();
        return chatList.stream()
                .map(chatDtoMapper::convertToDto)
                .collect(Collectors.toList());

    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> findAll(@PathVariable Integer page, @PathVariable Integer size) {

        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "updatedDate"));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page chats = chatService.findAll(pageable);
        List<Chat> chatList = chats.toList();
        List<ChatDto> chatDtoList = chatList.stream()
                .map(chatDtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(chatDtoList);
    }

    @PostMapping
    public void createChat() {

        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.getByEmail(auth).get();
        List<User> userList = new ArrayList<>();
        userList.add(user);
        List<Chat> userChats = user.getChats();
        Chat chat = new Chat();
        chat.setUsers(userList);
        userChats.add(chat);
        user.setChats(userChats);
        generalService.save(user);

    }

    /*@GetMapping("/{content}")
    public ResponseEntity<?> findByContent(@PathVariable String content) {

        List<Chat> chatList = defaultChatService.findByContent(content);
        List<ChatDto> chatDtoList = chatList.stream()
                .map(chatDtoMapper::convertToDto)
                .collect(Collectors.toList());
        if (chatList.isEmpty()) {
            return ResponseEntity.badRequest().body("Chat not found");
        } else {
            return ResponseEntity.ok(chatDtoList);
        }
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {

        Chat chat = chatService.getOne(id);
        if (chat == null) {
            return ResponseEntity.badRequest().body("Chat not found");
        }
        return ResponseEntity.ok().body(chatDtoMapper.convertToDto(chat));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteChat(@RequestBody ChatDtoRequest chatDtoRequest) {

        try {
            chatService.delete(chatDtoMapper.convertToEntity(chatDtoRequest));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long chatId) {

        try {
            chatService.deleteById(chatId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ChatDtoRequest chatDtoRequest) {

        try {
            Chat chatEntity = chatDtoMapper.convertToEntity(chatDtoRequest);
            chatEntity.setCreatedDate(chatService.getOne(chatEntity.getId()).getCreatedDate());
            chatEntity.setCreatedBy(chatService.getOne(chatEntity.getId()).getCreatedBy());
            chatService.save(chatEntity);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/participants")
    public ResponseEntity<?> addUsers(@PathVariable Long id) {

        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.getByEmail(auth).get();
        try {
            Chat chat = chatService.getOne(id);
            List<User> userList = chat.getUsers();
            userList.add(user);
            chat.setUsers(userList);
            List<Chat> userChats = user.getChats();
            userChats.add(chat);
            user.setChats(userChats);
            generalService.save(user);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<?> getChatsForUserExceptUserId(@PathVariable Long id) {

        try {
            List<ChatSpecDto> chatSpecDtoList = defaultChatService.getChatsForUserExceptUserId(id);
            return ResponseEntity.ok().body(chatSpecDtoList);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
