package org.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.*;
import org.finalproject.entity.Chat;
import org.finalproject.entity.Post;
import org.finalproject.entity.User;
import org.finalproject.service.DefaultChatService;
import org.finalproject.service.GeneralService;
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
@RequestMapping("/chats")
public class ChatRestController {

    private final GeneralService<Chat> chatService;
    private final DefaultChatService defaultChatService;
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
    public void createChat(@RequestBody UserRequestDto userRequestDto) {

        List<User> userList = List.of(userDtoMapper.convertToEntity(userRequestDto));
        Chat chat = new Chat(userList);
        chatService.save(chat);
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

//    @GetMapping("/search")
//    public ResponseEntity<?> findUserIdByPartOfName(@RequestBody String partOfName) {
//
//        List<Chat> chatList = defaultChatService.findUserIdByPartOfName(partOfName);
//        List<ChatDto> chatDtoList = chatList.stream()
//                .map(chatDtoMapper::convertToDto)
//                .collect(Collectors.toList());
//        if (chatList.isEmpty()) {
//            return ResponseEntity.badRequest().body("Chat not found");
//        } else {
//            return ResponseEntity.ok(chatDtoList);
//        }
//    }


}
