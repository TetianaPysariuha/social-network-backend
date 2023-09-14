package org.finalproject.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.chat.ChatDto;
import org.finalproject.dto.chat.ChatDtoMapper;
import org.finalproject.dto.chat.ChatSpecDto;
import org.finalproject.entity.Chat;
import org.finalproject.service.DefaultChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatRestController {

    private final DefaultChatService defaultChatService;
    private final ChatDtoMapper chatDtoMapper;

    //    @GetMapping
    //    public List<ChatDto> getAll() {
    //
    //        List<Chat> chatList = chatService.findAll();
    //        return chatList.stream()
    //                .map(chatDtoMapper::decorateDto)
    //                .collect(Collectors.toList());
    //    }
    //
    //    @GetMapping("/{page}/{size}")
    //    public ResponseEntity<?> findAll(@PathVariable Integer page, @PathVariable Integer size) {
    //
    //        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "updatedDate"));
    //        Pageable pageable = PageRequest.of(page, size, sort);
    //        Page chats = chatService.findAll(pageable);
    //        List<Chat> chatList = chats.toList();
    //        List<ChatDto> chatDtoList = chatList.stream()
    //                .map(chatDtoMapper::decorateDto)
    //                .collect(Collectors.toList());
    //        return ResponseEntity.ok(chatDtoList);
    //    }

    //    @PostMapping
    //    public void createChat() {
    //
    //        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    //        User user = userService.getByEmail(auth).get();
    //        List<User> userList = new ArrayList<>();
    //        userList.add(user);
    //        List<Chat> userChats = user.getChats();
    //        Chat chat = new Chat();
    //        chat.setUsers(userList);
    //        userChats.add(chat);
    //        user.setChats(userChats);
    //        generalUserService.save(user);
    //    }

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
    public ResponseEntity<ChatDto> getById(@PathVariable("id") Long id) {

        ChatDto chat = chatDtoMapper.decorateDto(defaultChatService.findEntityById(id));
        if (chat == null) {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().body(chat);
    }

    //    @DeleteMapping
    //    public ResponseEntity<?> deleteChat(@RequestBody ChatDtoRequest chatDtoRequest) {
    //
    //        try {
    //            chatService.delete(chatDtoMapper.convertToEntity(chatDtoRequest));
    //            return ResponseEntity.ok().build();
    //        } catch (RuntimeException e) {
    //            return ResponseEntity.badRequest().body(e.getMessage());
    //        }
    //    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long chatId) {

        defaultChatService.deleteChatById(chatId);
        return ResponseEntity.ok().build();
    }

    //    @PutMapping
    //    public ResponseEntity<?> update(@RequestBody ChatDtoRequest chatDtoRequest) {
    //
    //        try {
    //            Chat chatEntity = chatDtoMapper.convertToEntity(chatDtoRequest);
    //            chatEntity.setCreatedDate(chatService.getOne(chatEntity.getId()).getCreatedDate());
    //            chatEntity.setCreatedBy(chatService.getOne(chatEntity.getId()).getCreatedBy());
    //            chatService.save(chatEntity);
    //            return ResponseEntity.ok().build();
    //        } catch (RuntimeException e) {
    //            return ResponseEntity.badRequest().body(e.getMessage());
    //        }
    //    }

    //    @PutMapping("/{id}/participants")
    //    public ResponseEntity<?> addUsers(@PathVariable Long id) {
    //
    //        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    //        User user = userService.getByEmail(auth).get();
    //        try {
    //            Chat chat = chatService.getOne(id);
    //            List<User> userList = chat.getUsers();
    //            userList.add(user);
    //            chat.setUsers(userList);
    //            List<Chat> userChats = user.getChats();
    //            userChats.add(chat);
    //            user.setChats(userChats);
    //            generalService.save(user);
    //            return ResponseEntity.ok().build();
    //        } catch (RuntimeException e) {
    //            return ResponseEntity.badRequest().body(e.getMessage());
    //        }
    //    }

    @PutMapping("/{id}/participants/{userId}")
    public ResponseEntity<Void> addUser(@PathVariable("id") Long chatId, @PathVariable("userId") Long userId) {

        defaultChatService.addUserForChat(chatId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/participants")
    public ResponseEntity<List<ChatSpecDto>> getChatsForUserExceptUserId() {

        List<ChatSpecDto> chatSpecDtoList = defaultChatService.getChatsForUserExceptUserId();
        return ResponseEntity.ok().body(chatSpecDtoList);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<ChatDto> createNewChat(@PathVariable("id") Long userId) {

        Chat chat = defaultChatService.createNewChat(userId);
        return ResponseEntity.ok().body(chatDtoMapper.decorateDto(chat));
    }
}
